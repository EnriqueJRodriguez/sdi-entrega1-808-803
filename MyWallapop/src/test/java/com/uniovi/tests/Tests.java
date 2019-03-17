package com.uniovi.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobject.PO_HomeView;
import com.uniovi.tests.pageobject.PO_LoginView;
import com.uniovi.tests.pageobject.PO_OfferView;
import com.uniovi.tests.pageobject.PO_Properties;
import com.uniovi.tests.pageobject.PO_SignupView;
import com.uniovi.tests.pageobject.PO_UsersView;
import com.uniovi.tests.pageobject.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
////		En Windows (Debeserlaversión65.0.1y desactivar las actualizacioens	automáticas)):
//	static String PathFirefox64 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver022 = "C:\\Users\\UO258654\\Downloads\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
//		En GNU/Linux
	private static String PathFirefox65 = "/usr/bin/firefox";
	private static String Geckdriver024 = "/usr/bin/geckodriver";
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@Test
	public void PF01() {
		// Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// tests with empty fields
		PO_SignupView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777");
		PO_SignupView.checkKey(driver, "Error.signup.email.empty", PO_Properties.getSPANISH());
		PO_SignupView.fillForm(driver, "jos3f0@educastur.princast.es", "", "Perez", "77777", "77777");
		PO_SignupView.checkKey(driver, "Error.signup.name.empty", PO_Properties.getSPANISH());
		PO_SignupView.fillForm(driver, "jos3f0@educastur.princast.es", "Josefo", "", "77777", "77777");
		PO_SignupView.checkKey(driver, "Error.signup.lastName.empty", PO_Properties.getSPANISH());
		// Check the password not repeated correctly
		PO_SignupView.fillForm(driver, "jos3f0@educastur.princast.es", "Josefo", "Perez", "77777", "77776");
		PO_SignupView.checkKey(driver, "Error.signup.password.confirm", PO_Properties.getSPANISH());

		// Rellenamosel formulario.
		PO_SignupView.fillForm(driver, "admin@email.com", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		// COmprobamos el error de email repetido.
		PO_SignupView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	@Test
	public void PF02() {
		// Correct login for admin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "123456");
		PO_HomeView.checkKey(driver, "Menu.users", PO_Properties.getSPANISH());
		// Correct login for normal user
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "UO12345@uniovi.es", "123456");
		PO_HomeView.checkKey(driver, "Menu.logout", PO_Properties.getSPANISH());
		// Login without password
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "UO12345@uniovi.es", "");
		PO_HomeView.checkKey(driver, "Login.email", PO_Properties.getSPANISH());
		// Login without email
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "123456");
		PO_HomeView.checkKey(driver, "Login.email", PO_Properties.getSPANISH());
		// Login with the wrong password
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "UO12345@uniovi.es", "654321");
		PO_HomeView.checkKey(driver, "Login.email", PO_Properties.getSPANISH());
		// Login with a non existing email
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "rector@uniovi.es", "123456");
		PO_HomeView.checkKey(driver, "Login.email", PO_Properties.getSPANISH());
	}

	@Test
	public void PF03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "UO12345@uniovi.es", "123456");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		try {
			PO_View.checkKey(driver, "Menu.logout", PO_Properties.getSPANISH());
			fail();
		} catch (TimeoutException e) {

		}
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_View.checkKey(driver, "Login.email", PO_Properties.getSPANISH());
	}

	@Test
	public void PF04() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "123456");
		PO_HomeView.listUsers(driver);
		PO_HomeView.checkKey(driver, "User.1", PO_Properties.getSPANISH());
		PO_HomeView.checkKey(driver, "User.2", PO_Properties.getSPANISH());
		PO_HomeView.checkKey(driver, "User.3", PO_Properties.getSPANISH());
		PO_HomeView.checkKey(driver, "User.4", PO_Properties.getSPANISH());
		PO_HomeView.checkKey(driver, "User.5", PO_Properties.getSPANISH());
		PO_HomeView.checkKey(driver, "User.6", PO_Properties.getSPANISH());
	}

	@Test
	public void PF05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "123456");
		PO_HomeView.listUsers(driver);
		String[] users = { "users1" };
		PO_UsersView.fillForm(driver, users);
		try {
			PO_HomeView.checkKey(driver, "User.1", PO_Properties.getSPANISH());
		} catch (TimeoutException e) {

		}

		users[0] = "users10";
		PO_UsersView.fillForm(driver, users);
		try {
			PO_HomeView.checkKey(driver, "User.10", PO_Properties.getSPANISH());
		} catch (TimeoutException e) {

		}

		String[] users2 = { "users1", "users2", "users3" };
		PO_UsersView.fillForm(driver, users2);
		try {
			PO_HomeView.checkKey(driver, "User.2", PO_Properties.getSPANISH());
			PO_HomeView.checkKey(driver, "User.3", PO_Properties.getSPANISH());
			PO_HomeView.checkKey(driver, "User.4", PO_Properties.getSPANISH());
		} catch (TimeoutException e) {

		}
	}

	@Test
	public void PF06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "123456");
		PO_HomeView.addOffer(driver);
		PO_HomeView.checkKey(driver, "Offer.chorizo", PO_Properties.getSPANISH());

	}

	@Test
	public void PF07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ja@uniovi.es", "123456");
		PO_HomeView.seeMyOffers(driver);
		PO_HomeView.checkKey(driver, "Offer.raid", PO_Properties.getSPANISH());

	}

	@Test
	public void PF08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ja@uniovi.es", "123456");
		PO_HomeView.seeMyOffers(driver);
		By enlace = By.xpath("//td[contains(text(), 'RAID de sobremesa')]/following-sibling::*[3]");
		driver.findElement(enlace).click();
		try {
			PO_HomeView.checkKey(driver, "Offer.raid", PO_Properties.getSPANISH());
			fail();
		} catch (TimeoutException e) {

		}
		enlace = By.xpath("//td[contains(text(), 'Servidor de nombres')]//following-sibling::*[3]");
		driver.findElement(enlace).click();
		try {
			PO_HomeView.checkKey(driver, "Offer.names", PO_Properties.getSPANISH());
		} catch (TimeoutException e) {

		}
	}

	@Test
	public void PF09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ja@uniovi.es", "123456");
		PO_HomeView.seeOffers(driver);
		PO_OfferView.search(driver, "");
		assertTrue(PO_OfferView.getElements(driver) > 0);
		PO_OfferView.search(driver, "somier");
		try {
			PO_OfferView.getElements(driver);
			fail();
		} catch (TimeoutException e) {

		}

	}

	@Test
	public void PF10_1() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ja@uniovi.es", "123456");
		PO_HomeView.seeOffers(driver);
		PO_OfferView.search(driver, "Coche");
		By enlace = By.xpath("//td[contains(text(), 'Coche')]/following-sibling::*[5]");
		driver.findElement(enlace).click();
		driver.navigate().to("http://localhost:8090/home");
		PO_HomeView.checkKey(driver, "Home.myBalance1", PO_Properties.getSPANISH());
	}

	@Test
	public void PF10_2() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ja@uniovi.es", "123456");
		PO_HomeView.seeOffers(driver);
		PO_OfferView.search(driver, "Avion");
		By enlace = By.xpath("//td[contains(text(), 'Avion')]/following-sibling::*[5]");
		driver.findElement(enlace).click();
		driver.navigate().to("http://localhost:8090/home");
		PO_HomeView.checkKey(driver, "Home.myBalance2", PO_Properties.getSPANISH());
	}

	@Test
	public void PF10_3() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ja@uniovi.es", "123456");
		PO_HomeView.seeOffers(driver);
		PO_OfferView.search(driver, "Pluma");
		By enlace = By.xpath("//td[contains(text(), 'Pluma')]/following-sibling::*[5]");
		driver.findElement(enlace).click();
		PO_HomeView.checkKey(driver, "Products.noCash.title", PO_Properties.getSPANISH());
	}

	@Test
	public void PF011() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "UO12345@uniovi.es", "123456");
		PO_HomeView.seeMyPurchases(driver);
		By enlace = By.xpath("//td[contains(text(), 'Cigarrillo electrónico')]/following-sibling::*[3]");
		driver.findElement(enlace).click();

		PO_HomeView.checkKey(driver, "Purchase.cigarrilo", PO_Properties.getSPANISH());

		enlace = By.xpath("//td[contains(text(), 'Coche')]/following-sibling::*[3]");
		driver.findElement(enlace).click();
		PO_HomeView.checkKey(driver, "Purchase.coche", PO_Properties.getSPANISH());

	}

	@Test
	public void PF12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "123456");
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		
	}

}
