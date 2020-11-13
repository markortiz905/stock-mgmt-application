package com.stock.mgmt.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {


	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Id
	private String reference;

	@ManyToOne
	private Brand brand;

	private String denomination;

	private Double price;

	private Double weight;

	private Double volume;

	public Product(String reference, Brand brand, String denomination, Double price, Double weight, Double volume) {
		this.reference = reference;
		this.brand = brand;
		this.denomination = denomination;
		this.price = price;
		this.weight = weight;
		this.volume = volume;
	}

	public Product() {
	}

	public Long getId() {
		return id;
	}

	public String getReference() {
		return reference;
	}

	public Brand getBrand() {
		return brand;
	}

	public String getDenomination() {
		return denomination;
	}

	public Double getPrice() {
		return price;
	}

	public Double getWeight() {
		return weight;
	}

	public Double getVolume() {
		return volume;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", reference='" + reference + '\'' +
				", brand=" + brand +
				", denomination='" + denomination + '\'' +
				", price=" + price +
				", weight=" + weight +
				", volume=" + volume +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return getId().equals(product.getId()) &&
				getReference().equals(product.getReference());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getReference());
	}
}
