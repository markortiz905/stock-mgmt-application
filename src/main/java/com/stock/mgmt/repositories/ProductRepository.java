package com.stock.mgmt.repositories;

import com.stock.mgmt.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface ProductRepository extends CrudRepository<Product, String>{

	Product findProductByReference(String reference);
	
	default List<Product> getAllProducts() {
		List<Product> products
		 = new ArrayList<>();
		findAll().forEach( p -> {
			products.add(p);
		});
		return products;
	}
}
