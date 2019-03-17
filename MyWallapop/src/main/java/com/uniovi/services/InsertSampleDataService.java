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
		User dummy1 = new User("antonio@uniovi.es", "Antonio", "Pérez");
		dummy1.setPassword("123456");
		dummy1.setRole(rolesService.getRoles()[0]);
		User dummy2 = new User("federico@uniovi.es", "Federico", "Los Santos");
		dummy2.setPassword("123456");
		dummy2.setRole(rolesService.getRoles()[0]);
		User dummy3 = new User("santi@uniovi.es", "Santiago", "Abascal");
		dummy3.setPassword("123456");
		dummy3.setRole(rolesService.getRoles()[0]);
		User dummy4 = new User("mrajoy@uniovi.es", "Mariano", "Rajoy");
		dummy4.setPassword("123456");
		dummy4.setRole(rolesService.getRoles()[0]);
		User dummy5 = new User("psachez@uniovi.es", "Pedro", "Sanchez");
		dummy5.setPassword("123456");
		dummy5.setRole(rolesService.getRoles()[0]);

		User user1 = new User("UO12345@uniovi.es", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		user1.setBalance(10000);
		User user2 = new User("ja@uniovi.es", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setBalance(10000);
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("alb@uniovi.es", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("odo@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("admin@email.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("ededdedy@hotmail.com", "Edward", "Núñez");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);

		Product pr1 = new Product("Cigarrillo electrónico", "Cigarrillo electrónico sabor menta", 3.5, new Date());
		Product pr2 = new Product("Coche", "Opel corsa, semi nuevo", 5000.0, new Date());
		Product pr3 = new Product("Avion", "Bombardero stuka, de colección", 5000.0, new Date());
		Product pr4 = new Product("Repollos", "Recién salidos de la huerta", 3.5, new Date());
		Product pr5 = new Product("Malvadiscos", "Como el malo de cazafantasmas", 3.5, new Date());
		Product pr6 = new Product("Disco de estopa", "El de la raja de tu falda", 3.5, new Date());
		Product pr7 = new Product("Pluma", "Pluma estilográfica de gran calidad", 3.5, new Date());
		Product pr8 = new Product("El Quijote", "Edición de 1784", 3.5, new Date());
		Product pr9 = new Product("Rueda de bmw", "Recambio de ruedas de bmw", 3.5, new Date());
		Product pr10 = new Product("IMac", "En verdad es un debian tuneado", 3.5, new Date());
		Product pr12 = new Product("Botella de ron", "Del bueno, matusalén", 3.5, new Date());
		Product pr13 = new Product("Libro rojo de Mao", "Objeto histórico", 3.5, new Date());
		Product pr14 = new Product("Leche cruda", "Ahora legal en Cataluña", 3.5, new Date());
		Product pr15 = new Product("Tizas", "Paquete de diez, se venden juntas", 3.5, new Date());
		Product pr16 = new Product("Galletas prícipe", "Ligeramente caducadas", 3.5, new Date());
		Product pr17 = new Product("Anzuelos", "Sin estrenar, un chollo", 3.5, new Date());
		Product pr18 = new Product("Silent Hill", "EL de la PS2, el bueno", 3.5, new Date());
		Product pr19 = new Product("Máquina de humo", "En verdad es un vaper potente", 3.5, new Date());
		Product pr20 = new Product("RAID de sobremesa", "Me lo monté en una tarde", 3.5, new Date());
		Product pr21 = new Product("Servidor de nombres", "Solo local, que tampoco somos google", 3.5, new Date());

		usersService.addUser(dummy1);
		usersService.addUser(dummy2);
		usersService.addUser(dummy3);
		usersService.addUser(dummy4);
		usersService.addUser(user5);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(dummy5);

		productsService.addProduct(pr1, user1);
		productsService.addProduct(pr2, user1);
		productsService.addProduct(pr3, user1);
		productsService.addProduct(pr4, user1);
		productsService.addProduct(pr5, user1);
		productsService.addProduct(pr6, user1);
		productsService.addProduct(pr7, user1);
		productsService.addProduct(pr8, user1);
		productsService.addProduct(pr9, user1);
		productsService.addProduct(pr10, user1);
		productsService.addProduct(pr12, user1);
		productsService.addProduct(pr13, user1);
		productsService.addProduct(pr14, user1);
		productsService.addProduct(pr15, user1);
		productsService.addProduct(pr16, user1);
		productsService.addProduct(pr17, user1);
		productsService.addProduct(pr18, user1);
		productsService.addProduct(pr19, user1);
		productsService.addProduct(pr20, user2);
		productsService.addProduct(pr21, user2);
		
		productsService.buyProduct(pr1, user1);
		productsService.buyProduct(pr2, user1);
	}
}
