package by.exposit.delivery.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class OrderUpdateRequest {

    private Long orderId;
    private Long clientId;
    private Integer orderStatus;
}
