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
public class BrandServiceTest {

    @Autowired private BrandService brandService;
    @Autowired private ProductService productService;

    @Test
    void createBrand() {
        brandService.create("Apple", "China");
        assertThat(brandService.findBrand("Apple")).isNotNull();
    }

    @Test
    void updateBrand() {
        brandService.create("Apple", "China");
        assertThat(brandService.findBrand("Apple")).isNotNull();

        Brand brand = brandService.findBrand("Apple");
        brand.setCountry("US");
        brandService.update(brand);

        assertThat(brandService.findBrand("Apple")).matches(b->b.getCountry().equals("US"));
    }

    @Test
    void deleteBrand() {
        assertThat(brandService.create("Apple", "China")).isTrue();

        Brand brand = brandService.findBrand("Apple");
        assertThat(brandService.delete(brand)).isTrue();

        assertThat(brandService.findBrand("Apple")).isNull();
    }

    @Test
    void findBrand() {
        assertThat(brandService.create("Apple", "China")).isTrue();

        assertThat(brandService.findBrand("Apple")).isNotNull();
    }

    @Test
    void findAll() {
        assertThat(brandService.create("Apple", "China")).isTrue();

        assertThat(brandService.findAll()).isNotEmpty();
    }

    @Test
    void addAndListProducts() {
        assertThat(brandService.create("Apple", "China")).isTrue();
        assertThat(brandService.findAll()).isNotEmpty();

        Brand brand = brandService.findBrand("Apple");
        productService.create("IPHONE10", brand,
                "USD", 10000.0, 0.2, 0.0002);
        Product product = productService.findByReference("IPHONE10");
        brand.getProducts().add(product);
        brandService.update(brand);

        assertThat(brandService.findBrand("Apple").getProducts()).isNotEmpty();
    }

    @Test
    void deleteBrandWithProducts() {
        assertThat(brandService.create("Apple", "China")).isTrue();
        assertThat(brandService.findAll()).isNotEmpty();

        Brand brand = brandService.findBrand("Apple");
        productService.create("IPHONE10", brand,
                "USD", 10000.0, 0.2, 0.0002);
        Product product = productService.findByReference("IPHONE10");
        brand.getProducts().add(product);
        brandService.update(brand);

        assertThat(brandService.findBrand("Apple").getProducts()).isNotEmpty();

        assertThat(brandService.delete(brand)).isTrue();
        assertThat(brandService.findBrand("Apple")).isNull();

    }
}
