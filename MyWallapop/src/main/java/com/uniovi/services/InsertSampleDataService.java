package com.uniovi.services;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
@Service
public class InsertSampleDataService {
	@Autowired
	private UserService usersService;
	
	@Autowired
	private ProductsService productsService;

	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		User user1 = new User("99999990A", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("99999991B", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setBalance(10000);
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("99999992C", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("99999993D", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[1]);
		User user5 = new User("99999977E", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("99999988F", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		
		Product pr1 = new Product("Grincho vaporico","Grincho sabor mentoloide", 3.5, new Date());
		Product pr2 = new Product("Grincho vaporico","Grincho sabor limon", 3.5, new Date());
		Product pr3 = new Product("Grincho vaporico","Grincho sabor fresa", 3.5, new Date());
		Product pr4 = new Product("Grincho vaporico","Grincho sabor melocoton", 3.5, new Date());
		Product pr5 = new Product("Grincho vaporico","Grincho sabor malvadisco", 3.5, new Date());
		Product pr6 = new Product("Grincho vaporico","Grincho sabor mora", 3.5, new Date());
		Product pr7 = new Product("Grincho vaporico","Grincho sabor boli bic", 3.5, new Date());
		Product pr8 = new Product("Grincho vaporico","Grincho sabor bacon", 3.5, new Date());
		Product pr9 = new Product("Grincho vaporico","Grincho sabor bmw", 3.5, new Date());
		Product pr10 = new Product("Grincho vaporico","Grincho sabor malta", 3.5, new Date());
		Product pr12 = new Product("Grincho vaporico","Grincho sabor ron", 3.5, new Date());
		Product pr13 = new Product("Grincho vaporico","Grincho sabor vodka", 3.5, new Date());
		Product pr14 = new Product("Grincho vaporico","Grincho sabor patata", 3.5, new Date());
		Product pr15 = new Product("Grincho vaporico","Grincho sabor tiza", 3.5, new Date());
		Product pr16 = new Product("Grincho vaporico","Grincho sabor galleta", 3.5, new Date());
		Product pr17 = new Product("Grincho vaporico","Grincho sabor pasta de pescado", 3.5, new Date());
		Product pr18 = new Product("Grincho vaporico","Grincho sabor silent hill", 3.5, new Date());
		Product pr19 = new Product("Grincho vaporico","Grincho sabor hipster", 3.5, new Date());
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		productsService.addProduct(pr1,user1);
		productsService.addProduct(pr2,user1);
		productsService.addProduct(pr3,user1);
		productsService.addProduct(pr4,user1);
		productsService.addProduct(pr5,user1);
		productsService.addProduct(pr6,user1);
		productsService.addProduct(pr7,user1);
		productsService.addProduct(pr8,user1);
		productsService.addProduct(pr9,user1);
		productsService.addProduct(pr10,user1);
		productsService.addProduct(pr12,user1);
		productsService.addProduct(pr13,user1);
		productsService.addProduct(pr14,user1);
		productsService.addProduct(pr15,user1);
		productsService.addProduct(pr16,user1);
		productsService.addProduct(pr17,user1);
		productsService.addProduct(pr18,user1);
		productsService.addProduct(pr19,user1);
	}
}
