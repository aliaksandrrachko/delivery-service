package by.exposit.delivery.entities.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@SuperBuilder
@JsonPropertyOrder(value = {"countryCode", "nationalNumber"})
public class PhoneNumber implements Serializable {

    @JsonProperty(value = "countryCode")
    private String countryCode;

    @JsonProperty(value = "nationalNumber")
    private String nationalNumber;
}
