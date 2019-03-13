package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Product;
import com.uniovi.services.ProductsService;

@Controller
public class ProductsController {

	@Autowired // Service injection
	private ProductsService productsService;

	@RequestMapping("/product/list")
	public String getList() {
		return productsService.getProducts().toString();
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String setProduct(@ModelAttribute Product product) {
		productsService.addProduct(product);
		return "Ok";
	}

	@RequestMapping("/product/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return productsService.getProduct(id).toString();
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productsService.deleteProduct(id);
		return "Ok";
	}
	

}
