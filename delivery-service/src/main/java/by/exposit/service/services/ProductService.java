package by.exposit.service.services;

import by.exposit.delivery.api.dao.IProductDao;
import by.exposit.delivery.api.services.IProductService;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Singleton
public class ProductService implements IProductService {

    @InjectByType
    private IProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.getAll();
    }

    @Override
    public Product findById(Long id) {
        return productDao.get(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public Product create(Product entity) {
        Long lastKey = productDao.getLastKey();
        lastKey = lastKey == null ? 1L : lastKey + 1;
        entity.setId(lastKey);
        return productDao.create(entity).orElse(null);
    }

    @Override
    public Product update(Long id, Product entity) {
        return productDao.update(id, entity).orElse(null);
    }

    @Override
    public List<Product> findByCategoryNameIn(Set<String> categoriesName) {
        return productDao.findByCategoryNameIn(categoriesName);
    }

    @Override
    public List<Product> findByCategoryNameInAndPriceBetween(Set<String> categoriesName, BigDecimal priceFrom, BigDecimal priceTo) {
        return productDao.findByCategoryNameInAndPriceBetween(categoriesName, priceFrom, priceTo);
    }

    @Override
    public List<Product> findByStoreId(Long id) {
        return productDao.findByStoreId(id);
    }

    @Override
    public List<Product> findAllSortedByPrice() {
        return productDao.findAllSortedByPrice();
    }
}
