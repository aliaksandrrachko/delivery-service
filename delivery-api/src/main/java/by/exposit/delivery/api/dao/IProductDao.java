package by.exposit.delivery.api.dao;

import by.exposit.delivery.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IProductDao extends IAGenericDao<Product, Long> {
    List<Product> findByCategoryNameIn(Set<String> categoriesName);
    List<Product> findByCategoryNameInAndPriceBetween(Set<String> categoriesName, BigDecimal priceFrom, BigDecimal priceTo);
    List<Product> findByStoreId(Long id);
    List<Product> findAllSortedByPrice();
}
