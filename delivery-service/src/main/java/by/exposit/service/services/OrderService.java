package by.exposit.service.services;

import by.exposit.delivery.api.dao.IOrderDao;
import by.exposit.delivery.api.dao.IProductDao;
import by.exposit.delivery.api.dto.OrderCreateRequest;
import by.exposit.delivery.api.dto.OrderUpdateRequest;
import by.exposit.delivery.api.services.IClientService;
import by.exposit.delivery.api.services.IOrderService;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.entities.Client;
import by.exposit.delivery.entities.Order;
import by.exposit.delivery.entities.Product;
import by.exposit.delivery.entities.metadata.OrderStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class OrderService implements IOrderService {

    @InjectByType
    private IOrderDao orderDao;

    @InjectByType
    private IProductDao productDao;

    @InjectByType
    private IClientService clientService;

    @Override
    public List<Order> findAll() {
        return orderDao.getAll();
    }

    @Override
    public Order findById(Long id) {
        return orderDao.get(id).orElse(null);
    }

    @Override
    public Order create(final OrderCreateRequest request) {
        Client client = clientService.findById(request.getClientId());
        if (client == null){
            throw new NoSuchElementException(String.format("Client with id:'%s' not found.", request.getClientId()));
        } else if (request.getBasket() == null || request.getBasket().isEmpty()){
            throw new IllegalArgumentException(String.format("Basket for client:'%s' is empty", request.getClientId()));
        }
        List<Product> productList = takeProductsFromOrderRequest(request);
        Order order = Order.builder()
                .orderStatus(OrderStatus.CREATED)
                .client(client)
                .basket(request.getBasket())
                .build();
        order.setId(orderDao.getLastKey() + 1);
        Optional<Order> createdOrder = orderDao.create(order);
        if (createdOrder.isPresent()){
            productList.forEach(product -> productDao.update(product.getId(), product));
            return createdOrder.get();
        } else {
            return null;
        }
    }

    private List<Product> takeProductsFromOrderRequest(OrderCreateRequest request){
        Iterator<Map.Entry<Long, Integer>> mapIterator = request.getBasket().entrySet().iterator();
        List<Product> result = new ArrayList<>();
        while (mapIterator.hasNext()){
            Map.Entry<Long, Integer> entry = mapIterator.next();
            Long productId = entry.getKey();
            Integer countInBasket = entry.getValue();
            productDao.get(productId).ifPresent(product -> {
                int tookCount = takeProduct(product, countInBasket);
                if (tookCount == 0){
                    mapIterator.remove();
                } else {
                    request.getBasket().put(productId, tookCount);
                    result.add(product);
                }
            });
        }
        return result;
    }

    private int takeProduct(Product product, Integer count) {
        int availableCount = product.getCount();
        if (availableCount >= count){
            product.setCount(availableCount - count);
            return count;
        } else {
            product.setCount(0);
            return availableCount;
        }
    }

    @Override
    public Order updateStatus(Long id, OrderUpdateRequest request) {
        Optional<Order> optionalOrder = orderDao.get(id);
        OrderStatus requestStatus = OrderStatus.getOrderStatus(request.getOrderStatus());
        if (optionalOrder.isPresent()){
            Order orderFromDb = optionalOrder.get();
            switch (requestStatus){
                case ACCEPTED:
                case CONFIRMED:
                case DELIVERY:
                case DELIVERED:
                    if (requestStatus.getStatusCode() > orderFromDb.getOrderStatus().getStatusCode()) {
                        orderFromDb.setOrderStatus(OrderStatus.getOrderStatus(request.getOrderStatus()));
                        return orderDao.update(orderFromDb.getId(), orderFromDb).orElse(null);
                    } else {
                        break;
                    }
                case CANCELED:
                    List<Product> returnedProducts = returnProducts(orderFromDb);
                    orderFromDb.setOrderStatus(OrderStatus.CANCELED);
                    Optional<Order> updateOrderOptional = orderDao.update(orderFromDb.getId(), orderFromDb);
                    if (updateOrderOptional.isPresent()){
                        returnedProducts.forEach(product -> productDao.update(product.getId(), product));
                        return updateOrderOptional.get();
                    } else {
                        break;
                    }
                default: return null;
            }
        }
        return null;
    }

    private List<Product> returnProducts(Order order) {
        Iterator<Map.Entry<Long, Integer>> mapIterator = order.getBasket().entrySet().iterator();
        List<Product> result = new ArrayList<>();
        while (mapIterator.hasNext()){
            Map.Entry<Long, Integer> entry = mapIterator.next();
            Long productId = entry.getKey();
            Integer countInBasket = entry.getValue();
            productDao.get(productId).ifPresent(product -> {
                product.setCount(product.getCount() + countInBasket);
                order.getBasket().put(productId, 0);
                result.add(product);
            });
        }
        return result;
    }
}