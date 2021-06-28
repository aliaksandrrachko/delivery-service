package by.exposit.delivery.dao;

import static by.exposit.delivery.FileNameConstants.PRODUCT_FOLDER_PATH;

import by.exposit.delivery.api.dao.IProductDao;
import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.entities.Category;
import by.exposit.delivery.entities.Product;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ProductDao extends AGenericJsonFileDao<Product, Long> implements IProductDao {

    public ProductDao() {
        super(Product.class, PRODUCT_FOLDER_PATH);
        super.initCacheMethod();
    }

    @Override
    public List<Product> findByCategoryNameIn(Set<String> categoriesName) {
        return cache.parallelStream()
                .filter(
                        product -> product.getCategories().stream().map(Category::getCategoryName)
                                .anyMatch(categoryName -> categoriesName.parallelStream()
                                        .anyMatch(s -> categoryName.toLowerCase()
                                                .contains(categoryName.toLowerCase())))).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCategoryNameInAndPriceBetween(Set<String> categoriesName, BigDecimal priceFrom, BigDecimal priceTo) {
        return findByCategoryNameIn(categoriesName).parallelStream()
                .filter(product -> bigDecimalBetween(product.getPrice(), priceFrom, priceTo)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByStoreId(Long id) {
        return cache.parallelStream().filter(product -> product.getStoreId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllSortedByPrice() {
        return cache.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
    }

    private boolean bigDecimalBetween(BigDecimal price, BigDecimal priceFrom, BigDecimal priceTo){
        if (price == null) {
            return true;
        } else if (priceFrom != null && priceTo != null){
            return price.compareTo(priceFrom) >= 0 && price.compareTo(priceTo) <= 0;
        } else if (priceFrom != null){
            return price.compareTo(priceFrom) >= 0;
        } else if (priceTo != null){
            return price.compareTo(priceTo) <= 0;
        }
        return false;
    }


}
