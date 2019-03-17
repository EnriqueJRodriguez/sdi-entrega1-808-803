package com.uniovi.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Product;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		// None of the fields is empty
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.add.title.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.add.description.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.add.price.empty");

		// Check the requirements for the various fields
		checkTitle(product, errors);
		checkDescription(product, errors);
		checkPrice(product, errors);

	}

	private void checkTitle(Product product, Errors errors) {
		if (product.getTitle().length() < 5 || product.getTitle().length() > 24) {
			errors.rejectValue("email", "Error.add.title");
		}
	}

	private void checkDescription(Product product, Errors errors) {
		if (product.getDescription().length() < 5 || product.getDescription().length() > 24) {
			errors.rejectValue("description", "Error.add.description");
		}
	}

	private void checkPrice(Product product, Errors errors) {
		if (product.getPrice() < 0.01) {
			errors.rejectValue("price", "Error.add.price");
		}
	}
	
}
