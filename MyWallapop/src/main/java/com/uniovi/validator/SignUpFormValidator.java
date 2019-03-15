package com.uniovi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UserService;

@Component
public class SignUpFormValidator implements Validator {

	@Autowired
	private UserService usersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		// None of the fields is empty
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.signup.email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.signup.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.signup.lastName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.signup.password.empty");

		// Check the requirements for the various fields
		checkEmail(user, errors);
		checkName(user, errors);
		checkSurname(user, errors);
		checkPassword(user, errors);

	}

	private void checkEmail(User user, Errors errors) {
		if (usersService.getUserByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Error.signup.email.duplicate");
		}
	}

	private void checkName(User user, Errors errors) {
		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}
	}

	private void checkSurname(User user, Errors errors) {
		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.surname.length");
		}

	}

	private void checkPassword(User user, Errors errors) {
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if(!user.getPassword().equals(user.getPasswordConfirm())) {
			errors.rejectValue("password", "Error.signup.password.confirm");
		}

	}

}
