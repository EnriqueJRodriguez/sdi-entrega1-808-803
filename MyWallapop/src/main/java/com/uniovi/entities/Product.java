package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Double price;
	@JoinColumn(name = "user_id")
	private User user;

	public Product(Long id, String description, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public Product() {
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", price=" + price + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
