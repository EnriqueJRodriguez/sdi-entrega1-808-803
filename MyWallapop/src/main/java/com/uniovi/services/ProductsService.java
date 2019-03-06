package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
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

}
