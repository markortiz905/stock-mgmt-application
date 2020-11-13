package com.stock.mgmt.repositories;

import com.stock.mgmt.entities.Brand;
import com.stock.mgmt.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private ProductRepository productRepository;
    @Autowired private BrandRepository brandRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    void createProductScenario() {
        Brand brand = new Brand("Gaming Laptop", "Singapore");
        brandRepository.save(brand);
        Product product = new Product("IPHONE10", brand, "USD", 10000.0, 0.2, 0.0002);
        productRepository.save(product);

        brand.getProducts().add(product);
        brandRepository.save(brand);

        assertThat(productRepository.findProductByReference(product.getReference())).isNotNull();
        assertThat(brandRepository.findBrandByName(brand.getName()).getProducts()).isNotEmpty();
    }

    @Test
    void updateProductScenario() {
        Brand brand = new Brand("Gaming Laptop", "Singapore");
        brandRepository.save(brand);
        Product product = new Product("IPHONE10", brand, "USD", 10000.0, 0.2, 0.0002);
        productRepository.save(product);

        brand.getProducts().add(product);
        brandRepository.save(brand);

        assertThat(productRepository.findProductByReference(product.getReference())).isNotNull();
        assertThat(brandRepository.findBrandByName(brand.getName()).getProducts()).isNotEmpty();

        //update test
        Product productUpdate = productRepository.findProductByReference(product.getReference());
        productUpdate.setDenomination("PHP");
        productRepository.save(productUpdate);

        assertThat(productRepository.findProductByReference(productUpdate.getReference()))
                .matches(p->p.getDenomination().equals("PHP"));
    }

    @Test
    void findAllProductScenario() {
        Brand brand = new Brand("Gaming Laptop", "Singapore");
        brandRepository.save(brand);
        Product product = new Product("IPHONE10", brand, "USD", 10000.0, 0.2, 0.0002);
        productRepository.save(product);

        brand.getProducts().add(product);
        brandRepository.save(brand);

        assertThat(productRepository.getAllProducts()).isNotEmpty();
    }
}
