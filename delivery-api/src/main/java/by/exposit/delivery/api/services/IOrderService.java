package by.exposit.delivery.api.services;

import by.exposit.delivery.api.dto.OrderCreateRequest;
import by.exposit.delivery.api.dto.OrderUpdateRequest;
import by.exposit.delivery.entities.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findAll();
    Order findById(Long id);
    Order create(OrderCreateRequest request);
    Order updateStatus(Long id, OrderUpdateRequest request);
}
