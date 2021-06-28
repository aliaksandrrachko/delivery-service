package by.exposit.delivery.entities;

import by.exposit.delivery.entities.metadata.Address;
import by.exposit.delivery.entities.metadata.PhoneNumber;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@SuperBuilder
public class Store extends AEntity<Long>{

    private String email;
    private String name;
    private PhoneNumber phoneNumber;
    private Address address;
    private Map<String, String> additionalInfo;
}
