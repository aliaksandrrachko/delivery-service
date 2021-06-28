package by.exposit.delivery.api.services;

import by.exposit.delivery.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IProductService extends IAGenericService<Product, Long> {
    List<Product> findByCategoryNameIn(Set<String> categoriesName);
    List<Product> findByCategoryNameInAndPriceBetween(Set<String> categoriesName, BigDecimal priceFrom, BigDecimal priceTo);
    List<Product> findByStoreId(Long id);
    List<Product> findAllSortedByPrice();
}
