package com.uniovi.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private String password;
	@Transient
	private String passwordConfirm;
	private double balance;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Product> purchases;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Product> offers;

	public User(String email, String name, String lastName, String role) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.balance = 0.0;
	}

	public User() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return name + " " + lastName + " (" + email + ")";
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Product> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Product> purchases) {
		this.purchases = purchases;
	}

	public Set<Product> getOffers() {
		return offers;
	}

	public void setOffers(Set<Product> offers) {
		this.offers = offers;
	}

}
