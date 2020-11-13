package com.stock.mgmt.controllers;

import com.stock.mgmt.entities.Brand;
import com.stock.mgmt.entities.Product;
import com.stock.mgmt.services.BrandService;
import com.stock.mgmt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BrandController {

    @Autowired private BrandService brandService;
    @Autowired private ProductService productService;


    @PostMapping(value = "/brand", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void createBrand(String name, String country, HttpServletResponse response) {
        try {
            if (!brandService.create(name, country)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/brand", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void updateBrand(String name, String country, HttpServletResponse response) {
        try {
            Brand brand = brandService.findBrand(name);
            if (brand != null) {
                brand.setCountry(country);
                if (!brandService.update(brand)) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/brand/{name}")
    @ResponseBody
    public void deleteBrand(@PathVariable String name, HttpServletResponse response) {
        try {
            Brand brand = brandService.findBrand(name);
            if (brand != null) {
                if (!brandService.delete(brand)) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Brand> listBrand(HttpServletResponse response) {
        try {
            return brandService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ArrayList<>();
    }

    @GetMapping(value = "/brand/{name}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> listBrandProducts(@PathVariable String name, HttpServletResponse response) {
        try {
            Brand brand = brandService.findBrand(name);
            if (brand != null) {
                return brand.getProducts();
            }
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ArrayList<>();
    }

    @PostMapping(value = "/brand/{name}/products/{reference}")
    @ResponseBody
    public void addProduct(@PathVariable String name, @PathVariable String reference, HttpServletResponse response) {
        Product product = productService.findByReference(reference);
        if (product == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Brand brand = brandService.findBrand(name);
        if (brand == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            brand.getProducts().add(product);
            product.setBrand(brand);
            if (!productService.update(product) | !brandService.update(brand)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
