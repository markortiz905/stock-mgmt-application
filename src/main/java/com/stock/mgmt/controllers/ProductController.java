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
import java.util.List;

@Controller
public class ProductController {
    @Autowired private BrandService brandService;
    @Autowired private ProductService productService;

    @GetMapping("/product")
    @ResponseBody
    public List<Product> listProducts() {
        return productService.findAll();
    }

    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void createProduct(String reference, String brand, String denomination,
                                 Double price, Double weight, Double volume, HttpServletResponse response) {
        try{
            if (!productService.create(reference, brandService.findBrand(brand), denomination,
                    price, weight, volume)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void updateBrand(String reference, String brand, String denomination,
                               Double price, Double weight, Double volume, HttpServletResponse response) {
        try {
            Brand branded = brandService.findBrand(brand);
            if ( branded == null ) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            Product product = productService.findByReference(reference);
            if ( product == null ) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            product.setBrand(branded);
            product.setDenomination(denomination);
            product.setPrice(price);
            product.setWeight(weight);
            product.setVolume(volume);

            if ( !productService.update(product) ) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/product/{reference}")
    @ResponseBody
    public void createProduct(@PathVariable String reference, HttpServletResponse response) {
        try{
            if ( !productService.delete(reference) ) {
                if(productService.findByReference(reference) == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/product/{reference}")
    @ResponseBody
    public Product findProduct(@PathVariable String reference, HttpServletResponse response) {
        try {
            Product product = productService.findByReference(reference);
            if (product == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
