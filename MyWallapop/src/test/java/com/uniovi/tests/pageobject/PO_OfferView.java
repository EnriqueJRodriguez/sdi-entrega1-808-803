package com.uniovi.tests.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_OfferView extends PO_View {
	static public void fillForm(WebDriver driver, String titlep, String descriptionp, String pricep) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys(pricep);
		// Pulsarel botondeAlta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void search(WebDriver driver, String string) {
		WebElement title = driver.findElement(By.name("searchText"));
		title.click();
		title.clear();
		title.sendKeys(string);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	public static int getElements(WebDriver driver) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "product", getTimeout());
		return elementos.size();
	}
}
