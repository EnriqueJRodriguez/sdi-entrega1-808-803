package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.services.ProductsService;
import com.uniovi.services.UserService;

@Controller
public class ProductsController {

	@Autowired // Service injection
	private ProductsService productsService;

	@Autowired
	private UserService usersService;

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

	@RequestMapping("/product/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		Page<Product> products = productsService.getAllProducts(pageable);
		model.addAttribute("productList", products.getContent());
		return "product/list :: tableProducts";
	}

	@RequestMapping("/product/list")
	public String getList(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
//		String email = principal.getName();
//		User user = usersService.getUserByEmail(email);
		Page<Product> products = new PageImpl<Product>(new LinkedList<Product>());
		if (searchText != null && !searchText.isEmpty()) {
			products = productsService.getProductsByTitle(pageable, searchText);
		} else {
			products = productsService.getAllProducts(pageable);
		}
		products = productsService.getProductsByTitle(pageable, searchText);
		model.addAttribute("productList", products.getContent());
		model.addAttribute("page", products);
//		model.addAttribute("user", user);
		return "product/list";
	}

}
