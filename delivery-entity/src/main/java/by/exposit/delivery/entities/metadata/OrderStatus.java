package by.exposit.delivery.entities.metadata;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum OrderStatus {
    CREATED(1),
    ACCEPTED(2),
    CONFIRMED(3),
    DELIVERY(4),
    DELIVERED(5),
    CANCELED(6);

    private final int statusCode;

    private static final Map<Integer, OrderStatus> statuses;

    OrderStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    static {
        statuses = Stream.of(OrderStatus.values()).collect(Collectors.toMap(OrderStatus::getStatusCode, status -> status));
    }

    public static OrderStatus getOrderStatus(int statusCode) {
        return statuses.getOrDefault(statusCode, CREATED);
    }
}
