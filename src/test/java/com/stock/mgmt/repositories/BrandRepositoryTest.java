package com.stock.mgmt.repositories;

import static org.assertj.core.api.Assertions.*;

import com.stock.mgmt.entities.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@ActiveProfiles({"dev", "integration"})
public class BrandRepositoryTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private BrandRepository brandRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(brandRepository).isNotNull();
    }

    @Test
    void createBrandScenario() {
        Brand brand = new Brand("Gaming Laptop", "Singapore");
        brandRepository.save(brand);
    }

    @Test
    void listBrandScenario() {
        Brand brand = new Brand("GamingLaptop", "Singapore");
        brandRepository.save(brand);
        Brand brand_chair = new Brand("Gaming Chair", "Singapore");
        brandRepository.save(brand_chair);
        assertThat(brandRepository.getAllBrands()).isNotEmpty();
    }

    @Test
    void findBrandScenario() {
        Brand brand = new Brand("GamingLaptop1", "Singapore");
        brandRepository.save(brand);
        Brand brand_chair = new Brand("Gaming Chair1", "Singapore");
        brandRepository.save(brand_chair);
        assertThat(brandRepository.findBrandByName("GamingLaptop1"))
                .matches(brand1 -> brand1.getName().equals("GamingLaptop1"));
    }
}
