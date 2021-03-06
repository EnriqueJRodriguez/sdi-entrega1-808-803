package com.uniovi.tests.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_View {
	static public void checkWelcome(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_HomeView.checkWelcome(driver, locale1);
		// Cambiamosa segundoidioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos queel textodebienvenidahayacambiadoa segundoidioma
		PO_HomeView.checkWelcome(driver, locale2);
		// Volvemosa Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_HomeView.checkWelcome(driver, locale1);
	}

	/***
	 * Seleccionael enlacedeidiomacorrespondientealtextotextLanguage * @paramdriver:
	 * apuntandoalnavegadorabiertoactualmente.* @paramtextLanguage: el
	 * textoqueapareceenel enlacedeidioma("English" o "Spanish")
	 */
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		// clickamoslaopciónIdioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		// Esperamosa queaparezcael menúdeopciones.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
		// SeleniumUtils.esperarSegundos(driver, 2);//CLickamos la opción
		// IngléspartiendodelaopciónEspañol
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
	}
	public static void listUsers(WebDriver driver) {
		// clickamoslaopciónIdioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", getTimeout());
		elementos.get(0).click();
		// Esperamosa queaparezcael menúdeopciones.
	//	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "user-options-list", getTimeout());
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "listUsers", getTimeout());
		elementos.get(0).click();
	}
	
	public static void addOffer(WebDriver driver) {
		// clickamoslaopciónIdioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "addProduct", getTimeout());
		elementos.get(0).click();
	}
	public static void seeMyOffers(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "ListMyOffers", getTimeout());
		elementos.get(0).click();
	}
	public static void seeOffers(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "listProducts", getTimeout());
		elementos.get(0).click();
	}

	public static void seeMyPurchases(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "listPurchases", getTimeout());
		elementos.get(0).click();
	}
		
	
	
}
