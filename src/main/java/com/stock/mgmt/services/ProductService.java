package com.stock.mgmt.services;

import com.stock.mgmt.entities.Brand;
import com.stock.mgmt.entities.Product;
import com.stock.mgmt.repositories.BrandRepository;
import com.stock.mgmt.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;
    @Autowired private BrandRepository brandRepository;

    public boolean create(String reference, Brand brand, String denomination,
                          Double price, Double weight, Double volume) {
        try {
            Product product = new Product(reference, brand, denomination, price, weight, volume);
            productRepository.save(product);
            brand.getProducts().add(product);
            brandRepository.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String reference) {
        try {
            delete(productRepository.findProductByReference(reference));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Product product) {
        try {
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> findAll() {
        return productRepository.getAllProducts();
    }

    public Product findByReference(String reference) {
        return productRepository.findProductByReference(reference);
    }
}
