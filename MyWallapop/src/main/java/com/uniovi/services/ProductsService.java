package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		productsRepository.findAll().forEach(products::add);
		return products;
	}

	public Product getProduct(Long id) {
		return productsRepository.findById(id).get();
	}

	public void addProduct(Product product) {
		// If the id is null we assign the last +1 position on the list
		productsRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productsRepository.deleteById(id);
	}
	
	public Page<Product> getAllProducts(Pageable pageable) {
		return productsRepository.searchAllProducts(pageable);
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

	public boolean buyProduct(Product p, User buyer) {
		if(p.getPrice() <= buyer.getBalance()) {
			p.getOwner().setBalance(p.getOwner().getBalance() + p.getPrice());
			buyer.setBalance(buyer.getBalance() - p.getPrice());
			buyer.getPurchases().add(p);
			p.setSold(true);
			return true;
		}
		return false;
	}
	
}
