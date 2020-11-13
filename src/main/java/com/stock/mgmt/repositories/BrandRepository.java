package com.stock.mgmt.repositories;

import com.stock.mgmt.entities.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface BrandRepository extends CrudRepository<Brand, String>{

	Brand findBrandByName(String name);
	
	default List<Brand> getAllBrands() {
		List<Brand> brands
		 = new ArrayList<>();
		findAll().forEach( p -> {
			brands.add(p);
		});
		return brands;
	}
}
