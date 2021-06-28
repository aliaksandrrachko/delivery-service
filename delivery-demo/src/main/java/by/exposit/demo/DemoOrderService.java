package by.exposit.demo;

import by.exposit.delivery.api.dto.OrderCreateRequest;
import by.exposit.delivery.api.dto.OrderUpdateRequest;
import by.exposit.delivery.api.services.IOrderService;
import by.exposit.delivery.api.utils.IDemo;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.entities.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DemoOrderService implements IDemo {

    @InjectByType
    private IOrderService orderService;

    public void demo() {
        log.info("Find all orders: '{}' orders found.", orderService.findAll().size());

        Order createdOrder = orderService.create(getDemoOrderCreateRequest());
        log.info("Create order: {}.", createdOrder);
        log.info("Find created order by id='{}': {}.", createdOrder.getId(), orderService.findById(createdOrder.getId()));

        OrderUpdateRequest updateRequest = getDemoOrderUpdateRequest();
        updateRequest.setOrderId(createdOrder.getId());
        log.info("Update order status for created order: {}", updateRequest);
        Order updatedStatusOrder = orderService.updateStatus(createdOrder.getId(), updateRequest);
        log.info("Find update status order by id='{}': {}.", updatedStatusOrder.getId(), orderService.findById(updatedStatusOrder.getId()));

        log.info("Cancel updated order.");
        Order canceledOrder = orderService.updateStatus(updatedStatusOrder.getId(), getDemoUndoOrderUpdateRequest());
        log.info("Get canceled order: {}", canceledOrder);
    }

    private OrderCreateRequest getDemoOrderCreateRequest(){
        return OrderCreateRequest.builder()
                .clientId(1L)
                .basket(new HashMap<>(Map.of(2L, 3, 3L, 4)))
                .build();
    }

    private OrderUpdateRequest getDemoOrderUpdateRequest(){
        return OrderUpdateRequest.builder()
                .orderStatus(2)
                .build();
    }

    private OrderUpdateRequest getDemoUndoOrderUpdateRequest(){
        return OrderUpdateRequest.builder()
                .orderStatus(6)
                .build();
    }
}
