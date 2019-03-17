package com.uniovi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProductsRepository;
import com.uniovi.repositories.UserRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private UserRepository usersRepository;
	
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		productsRepository.findAll().forEach(products::add);
		return products;
	}

	public Product getProduct(Long id) {
		return productsRepository.findById(id).get();
	}

	public void addProduct(Product product, User owner) {
		// If the id is null we assign the last +1 position on the list
		if(product.getDate() == null) {
			product.setDate(new Date());
		}
		product.setOwner(owner);
		owner.getOffers().add(product);
		usersRepository.save(owner);
		productsRepository.save(product);
	}

	public void deleteProduct(Long id, String email) {
		if(getProduct(id).getOwner().getEmail().equals(email)) {
			User u = usersRepository.findByEmail(email);
			u.getOffers().remove(getProduct(id));
			usersRepository.save(u);
			productsRepository.deleteById(id);
		}
	}
	
	public Page<Product> getAllProducts(Pageable pageable) {
		return productsRepository.searchAllProducts(pageable);
	}
	
	public Page<Product> getAllPurchases(Pageable pageable, String email) {
		return productsRepository.searchAllPurchases(pageable,email);
	}
	
	public Page<Product> getProductsByTitle(Pageable pageable, String searchText) {
		searchText = "%" + searchText + "%";
		if (searchText.isEmpty() && searchText != null) {
			return getAllProducts(pageable);
		}
		else {
			return productsRepository.searchProductByTitle(pageable, searchText);
		}
	}
	
	public Page<Product> getPurchasesByTitle(Pageable pageable, String searchText, String email) {
		searchText = "%" + searchText + "%";
		if (searchText.isEmpty() && searchText != null) {
			return getAllPurchases(pageable, email);
		}
		else {
			return productsRepository.searchPurchasesByTitle(pageable, searchText,email);
		}
	}

	public boolean buyProduct(Product p, User buyer) {
		if(p.getPrice() <= buyer.getBalance()) {
			p.getOwner().setBalance(p.getOwner().getBalance() + p.getPrice());
			buyer.setBalance(buyer.getBalance() - p.getPrice());
			buyer.getPurchases().add(p);
			p.setBuyer(buyer);
			p.setSold(true);
			productsRepository.save(p);
			usersRepository.save(buyer);
			usersRepository.save(p.getOwner());
			return true;
		}
		return false;
	}

	public Page<Product> getAllOffers(Pageable pageable, String email) {
		return productsRepository.searchAllOffers(pageable,email);
	}

	public Page<Product> getOffersByTitle(Pageable pageable, String searchText, String email) {
		searchText = "%" + searchText + "%";
		if (searchText.isEmpty() && searchText != null) {
			return getAllOffers(pageable, email);
		}
		else {
			return productsRepository.searchOfferByTitle(pageable, searchText,email);
		}
	}
	
}
