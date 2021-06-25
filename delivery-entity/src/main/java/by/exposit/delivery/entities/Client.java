package by.exposit.delivery.entities;

import by.exposit.delivery.entities.metadata.Address;
import by.exposit.delivery.entities.metadata.Gender;
import by.exposit.delivery.entities.metadata.PhoneNumber;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@SuperBuilder
public class Client  extends AEntity<Long> {

    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private PhoneNumber phoneNumber;
    private Address address;
    @Builder.Default
    private Gender gender = Gender.UNKNOWN;
    private LocalDate birthday;
}
