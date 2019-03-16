package com.uniovi.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;
	private Double price;
	@ManyToOne@JoinColumn(name = "owner_id")
	private User owner;
	private Date date;
	@ManyToOne@JoinColumn(name = "buyer_id")
	private User buyer;

	public Product(Long id,String title, String description, Double price) {
		super();
		this.id = id;
		this.title = title;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	
	
	

}
