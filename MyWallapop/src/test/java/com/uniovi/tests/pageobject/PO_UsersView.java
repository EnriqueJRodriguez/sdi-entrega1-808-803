package com.uniovi.tests.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_UsersView extends PO_View {
	static public void fillForm(WebDriver driver, String[] emails ) {
		for(String email : emails) {
			WebElement checkBox = driver.findElement(By.id(email));
			checkBox.click();
		}
		// Pulsarel botondeAlta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
