package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.services.ProductsService;
import com.uniovi.services.UserService;
import com.uniovi.validator.ProductValidator;

@Controller
public class ProductsController {

	@Autowired // Service injection
	private ProductsService productsService;

	@Autowired
	private UserService usersService;

	@Autowired
	private ProductValidator productValidator;

	@RequestMapping(value = "/product/add", method = RequestMethod.GET)
	public String addProductGet(Model model) {
		model.addAttribute("product", new Product());
		return "product/add";
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String addProductPost(@Validated Product product, BindingResult result) {
		productValidator.validate(product, result);
		if (result.hasErrors()) {
			return "product/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		User owner = usersService.getUserByEmail(email);
		productsService.addProduct(product, owner);
		return "redirect:/product/offer";
	}

	@RequestMapping("/product/remove/{id}")
	public String deleteProduct(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		productsService.deleteProduct(id, email);
		return "redirect:/product/offer";
	}

	@RequestMapping("/product/buy/{id}")
	public String buyProduct(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		// String email = securityService.findLoggedInEmail(); // Gets session's user
		// identifier
		User buyer = usersService.getUserByEmail(email);
		Product p = productsService.getProduct(id);
		if (productsService.buyProduct(p, buyer)) {
			return "redirect:/product/list";
		}
		return "/product/noCash";
	}

	@RequestMapping("/product/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal) {
		Page<Product> products = productsService.getAllProducts(pageable);
		model.addAttribute("productList", products.getContent());
		return "product/list :: tableProducts";
	}

	@RequestMapping("/product/purchase/update")
	public String updatePurchase(Model model, Pageable pageable, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		Page<Product> products = productsService.getAllPurchases(pageable, email);
		model.addAttribute("productList", products.getContent());
		return "product/purchase :: tablePurchase";
	}

	@RequestMapping("/product/offer/update")
	public String updateOffer(Model model, Pageable pageable, Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		Page<Product> products = productsService.getAllOffers(pageable, email);
		model.addAttribute("productList", products.getContent());
		return "product/offer :: tableOffer";
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
		model.addAttribute("productList", products.getContent());
		model.addAttribute("page", products);
//		model.addAttribute("user", user);
		return "product/list";
	}

	@RequestMapping("/product/purchase")
	public String getPurchases(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
//		String email = principal.getName();
//		User user = usersService.getUserByEmail(email);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		Page<Product> products = new PageImpl<Product>(new LinkedList<Product>());
		if (searchText != null && !searchText.isEmpty()) {
			products = productsService.getPurchasesByTitle(pageable, searchText, email);
		} else {
			products = productsService.getAllPurchases(pageable, email);
		}
		model.addAttribute("productList", products.getContent());
		model.addAttribute("page", products);
//		model.addAttribute("user", user);
		return "product/purchase";
	}

	@RequestMapping("/product/offer")
	public String getOffers(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
//		String email = principal.getName();
//		User user = usersService.getUserByEmail(email);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		Page<Product> products = new PageImpl<Product>(new LinkedList<Product>());
		if (searchText != null && !searchText.isEmpty()) {
			products = productsService.getOffersByTitle(pageable, searchText, email);
		} else {
			products = productsService.getAllOffers(pageable, email);
		}
		model.addAttribute("productList", products.getContent());
		model.addAttribute("page", products);
//		model.addAttribute("user", user);
		return "product/offer";
	}

}
