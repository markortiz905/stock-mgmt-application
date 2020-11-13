package com.stock.mgmt.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Brand {


	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Id
	private String name;

	private String country;

	@OneToMany(mappedBy = "brand")
	private List<Product> products  = new ArrayList<>();

	public Brand() {}

	public Brand(String name, String country) {
		this.name = name;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Brand{" +
				"id=" + id +
				", name='" + name + '\'' +
				", country='" + country + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Brand brand = (Brand) o;
		return getId().equals(brand.getId()) &&
				getName().equals(brand.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
