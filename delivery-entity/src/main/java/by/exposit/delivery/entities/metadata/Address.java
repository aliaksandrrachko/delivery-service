package by.exposit.delivery.entities.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address implements Serializable {

    private String country;
    private String region;
    private String district;
    private String cityName;
    private String streetName;
    private String postalCode;
    private String houseNumber;
    private String apartmentNumber;
}