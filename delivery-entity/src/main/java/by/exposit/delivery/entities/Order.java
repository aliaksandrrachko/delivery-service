package by.exposit.delivery.entities;

import by.exposit.delivery.entities.metadata.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@SuperBuilder
public class Order extends AEntity<Long>{

    private OrderStatus orderStatus;
    private Client client;

    /**
     * Map of products id and amount
     */
    @Builder.Default
    private Map<Long, Integer> basket = new HashMap<>();
}
