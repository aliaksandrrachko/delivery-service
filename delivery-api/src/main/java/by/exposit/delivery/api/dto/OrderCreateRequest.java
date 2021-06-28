package by.exposit.delivery.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class OrderCreateRequest {

    private Long clientId;
    /**
     * Map of products id and amount
     */
    private Map<Long, Integer> basket;
}
