package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Product;

public interface ProductsRepository  extends CrudRepository<Product, Long>{
	
	@Query("SELECT r FROM Product r WHERE (LOWER(r.title) LIKE LOWER(?1)) and r.sold = 0")
	Page<Product> searchProductByTitle(Pageable pageable, String searchText);
	
	@Query("SELECT r FROM Product r WHERE r.sold = 0")
	Page<Product> searchAllProducts(Pageable pageable);
}
