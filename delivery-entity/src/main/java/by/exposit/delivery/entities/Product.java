package by.exposit.delivery.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@SuperBuilder
public class Product extends AEntity<Long>{

    private String title;
    private BigDecimal price;
    private int count;
    private Long storeId;

    @Builder.Default
    private Map<String, String> attributes = new HashMap<>();
    private Set<Category> categories;
}