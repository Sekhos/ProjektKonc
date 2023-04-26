package com.example.projektkonc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);


        return "users";
    }
    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add new user");

        return "user_form";

    }

    @PostMapping("/user/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message","User has been saved sucessfully");
        return "redirect:/users";

    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle","Edit user (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message","User has been saved sucessfully");
            return "redirect:/users";

        }
    }

}