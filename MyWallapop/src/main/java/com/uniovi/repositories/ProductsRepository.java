package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Product;

public interface ProductsRepository  extends CrudRepository<Product, Long>{
	
	@Query("SELECT r FROM Product r WHERE (LOWER(r.title) LIKE LOWER(?1)) ")
	Page<Product> searchProductByTitle(Pageable pageable, String searchText);
	
	@Query("SELECT r FROM Product r ")
	Page<Product> searchAllProducts(Pageable pageable);

	@Query("SELECT r FROM Product r WHERE r.buyer.email = (?1)")
	Page<Product> searchAllPurchases(Pageable pageable, String email);

	@Query("SELECT r FROM Product r WHERE (LOWER(r.title) LIKE LOWER(?1)) and (r.buyer.email = (?2)) ")
	Page<Product> searchPurchasesByTitle(Pageable pageable, String searchText, String email);

	@Query("SELECT r FROM Product r WHERE r.owner.email = (?1)")
	Page<Product> searchAllOffers(Pageable pageable, String email);

	@Query("SELECT r FROM Product r WHERE (LOWER(r.title) LIKE LOWER(?1)) and (r.owner.email = (?2)) ")
	Page<Product> searchOfferByTitle(Pageable pageable, String searchText, String email);
}
