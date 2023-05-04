package com.example.projektkonc.controller;

import com.example.projektkonc.model.User;
import com.example.projektkonc.exception.UserNotFoundException;
import com.example.projektkonc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String showUserList(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("pageTitle", "Add new user");
		return "user_form";
	}

	@PostMapping("/save")
	public String saveUser(User user, RedirectAttributes ra) {
		userService.save(user);
		ra.addFlashAttribute("message", "User has been saved successfully");
		return "redirect:/user";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			User user = userService.get(id);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
			return "user_form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", "User has been saved sucessfully");
			return "redirect:/user";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteUserById(id);
		return "redirect:/user";
	}
}