package by.exposit.demo;

import by.exposit.delivery.api.services.IProductService;
import by.exposit.delivery.api.utils.IDemo;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.entities.Category;
import by.exposit.delivery.entities.Product;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class DemoProductService implements IDemo {

    @InjectByType
    private IProductService productService;

    public void demo() {
        log.info("Find all products: '{}' products found.", productService.findAll().size());

        Product createdProduct = productService.create(getTestProduct());
        log.info("Create product: {}.", createdProduct);
        log.info("Find created product by id='{}': {}.", createdProduct.getId(), productService.findById(createdProduct.getId()));

        Product productForUpdate = createdProduct;
        productForUpdate.setPrice(new BigDecimal("4000"));
        log.info("Product for update: {}.", productForUpdate);
        Product updateProduct = productService.update(productForUpdate.getId(), productForUpdate);
        log.info("Find updated product by id='{}': {}.", productForUpdate.getId(), productService.findById(updateProduct.getId()));

        log.info("Delete created product with id='{}'.", createdProduct.getId());
        productService.delete(createdProduct.getId());

        log.info("Find deleted product by id='{}':{}", createdProduct.getId(), productService.findById(createdProduct.getId()));

        log.info("Find products by category: products found '{}'", productService.findByCategoryNameIn(getCategoriesName()).size());

        log.info("Find products by category and price between: products found '{}'.",
                productService.findByCategoryNameInAndPriceBetween(getCategoriesName(), null, new BigDecimal("6000")));

        log.info("Find products by store id: products found '{}'", productService.findByStoreId(1L));
    }

    private Product getTestProduct() {
        return Product.builder()
                .title("MacBook Pro")
                .price(new BigDecimal("3699.09"))
                .count(10)
                .storeId(1L)
                .attributes(new HashMap<>(Map.of("year", "2020")))
                .categories(new HashSet<>(Set.of(new Category("computer"), new Category("mac"))))
                .build();
    }

    private Set<String> getCategoriesName() {
        return new HashSet<>(Set.of("computer", "notebook", "mac"));
    }
}
