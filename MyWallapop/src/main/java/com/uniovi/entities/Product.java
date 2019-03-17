package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String description;
	private Double price;
	@ManyToOne@JoinColumn(name = "owner_id")
	private User owner = null;
	private Date date;
	@ManyToOne@JoinColumn(name = "buyer_id")
	private User buyer = null;
	@Type(type= "org.hibernate.type.NumericBooleanType")
	private boolean sold;

	public Product(String title, String description, Double price, Date date, User owner) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = date;
		this.owner = owner;
		this.sold = false;
	}

	public Product() {
	}

	

	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + ", price=" + price + ", owner=" + owner
				+ ", date=" + date + "]";
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

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

}
