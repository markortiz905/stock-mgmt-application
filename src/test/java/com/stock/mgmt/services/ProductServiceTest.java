package com.stock.mgmt.services;

import com.stock.mgmt.StockMgmtApplication;
import com.stock.mgmt.entities.Brand;
import com.stock.mgmt.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StockMgmtApplication.class, BrandService.class, ProductService.class})
@DataJpaTest
public class ProductServiceTest {
    @Autowired private BrandService brandService;
    @Autowired private ProductService productService;

    @Test
    void createProduct() {
        brandService.create("Apple", "China");
        assertThat(brandService.findBrand("Apple")).isNotNull();

        Brand brand = brandService.findBrand("Apple");
        assertThat(productService.create("IPHONE10", brand,
                "USD", 10000.0, 0.2, 0.0002)).isTrue();
    }

    @Test
    void updateProduct() {
        brandService.create("Apple", "China");
        assertThat(brandService.findBrand("Apple")).isNotNull();

        Brand brand = brandService.findBrand("Apple");
        assertThat(productService.create("IPHONE10", brand,
                "USD", 10000.0, 0.2, 0.0002)).isTrue();

        Product product = productService.findByReference("IPHONE10");
        product.setDenomination("PHP");

        productService.update(product);

        assertThat(productService.findByReference("IPHONE10").getDenomination()).isEqualTo("PHP");
    }

    @Test
    void deleteProduct() {
        brandService.create("Apple", "China");
        assertThat(brandService.findBrand("Apple")).isNotNull();

        Brand brand = brandService.findBrand("Apple");
        assertThat(productService.create("IPHONE10", brand,
                "USD", 10000.0, 0.2, 0.0002)).isTrue();

        Product product = productService.findByReference("IPHONE10");
        assertThat(productService.delete(product)).isTrue();
    }
}
