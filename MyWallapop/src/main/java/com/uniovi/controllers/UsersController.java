package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.uniovi.entities.User;
import com.uniovi.entities.UsersBean;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;
import com.uniovi.validator.SignUpFormValidator;

@Controller
public class UsersController {
	@Autowired
	private UserService usersService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	@Autowired
	private RolesService rolesService;

	/**
	 * Returns the view corresponding to the list of users
	 * 
	 * @param model
	 * @return the view with the user's list
	 */
	@RequestMapping("/user/list")
	public String getListado(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email2 = auth.getName();
		User user2 = usersService.getUserByEmail(email2);
		if(user2.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		List<String> users = new ArrayList<String>();
		usersService.getUsers().forEach((user) -> users.add(user.getData()));
		model.addAttribute("currentUsers", usersService.getUsers());
		model.addAttribute("usersToRemove", new UsersBean());
		return "user/list";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email2 = auth.getName();
		User user2 = usersService.getUserByEmail(email2);
		if(user2.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String deleteUsers(@ModelAttribute("usersToRemove") UsersBean usersToRemove) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email2 = auth.getName();
		User user2 = usersService.getUserByEmail(email2);
		if(user2.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		usersToRemove.getUsers().forEach((email) -> {
			// String email = userData.substring(userData.indexOf("(") + 1,
			// userData.indexOf(")"));
			User user = usersService.getUserByEmail(email);
			usersService.deleteUser(user.getId());
		});
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email2 = auth.getName();
		User user2 = usersService.getUserByEmail(email2);
		if(user2.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		if(user.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email2 = auth.getName();
		User user2 = usersService.getUserByEmail(email2);
		if(user2.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email2 = auth.getName();
		User user2 = usersService.getUserByEmail(email2);
		if(user2.getRole().equals("ROLE_CUSTOMER")) {
			return "redirect:/login";
		}
		user.setId(id);
		usersService.addUser(user);
		return "redirect:/user/details/" + id;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return "redirect:/login";
		}
		String email = auth.getName();
		User user = usersService.getUserByEmail(email);
		model.addAttribute("user", user);
		return "home";
	}
}
