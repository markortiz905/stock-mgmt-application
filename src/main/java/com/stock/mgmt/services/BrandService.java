package com.stock.mgmt.services;

import com.stock.mgmt.entities.Brand;
import com.stock.mgmt.entities.Product;
import com.stock.mgmt.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    @Autowired private BrandRepository brandRepository;

    public boolean create(String name, String country) {
        try {
            brandRepository.save(new Brand(name, country));
            return brandRepository.existsById(name);
        } catch (Exception e) {
            //handle error for business if needed
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Brand brand) {
        try {
            brandRepository.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Brand brand) {
        try {
            brandRepository.delete(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Brand findBrand(String brandName) {
        try {
            return brandRepository.findBrandByName(brandName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Brand> findAll() {
        return brandRepository.getAllBrands();
    }

    public List<Product> listProducts(String brandName) {
        try {
            return brandRepository.findBrandByName(brandName).getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean addProduct(Brand brand, Product product) {
        try {
            brand.getProducts().add(product);
            brandRepository.save(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
