package com.uniovi.tests.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_UsersView extends PO_View {
	static public void fillForm(WebDriver driver, String[] emails) {
		for (String email : emails) {
			WebElement checkBox = driver.findElement(By.id(email));
			checkBox.click();
		}
		// Pulsarel botondeAlta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	static public void checkWelcome(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("User.list.title", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_UsersView.checkWelcome(driver, locale1);
		// Cambiamosa segundoidioma
		PO_UsersView.changeIdiom(driver, textIdiom2);
		// COmprobamos queel textodebienvenidahayacambiadoa segundoidioma
		PO_UsersView.checkWelcome(driver, locale2);
		// Volvemosa Español.
		PO_UsersView.changeIdiom(driver, textIdiom1);
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_UsersView.checkWelcome(driver, locale1);
	}

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
}
