package by.exposit.delivery.entities;

import by.exposit.delivery.entities.metadata.Address;
import by.exposit.delivery.entities.metadata.PhoneNumber;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Store extends AEntity<Long>{

    private String name;
    private Address address;
    private PhoneNumber phoneNumber;
    private Map<String, String> additionalInfo;

    private Set<Product> products;
}
