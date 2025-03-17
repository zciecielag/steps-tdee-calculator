package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.entity.user.User;
import com.example.steps_tdee_calculator.repository.UserRepository;
import com.example.steps_tdee_calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "loginPage";
    }

    @PostMapping("/loginForm")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        if (userService.verifyUser(user.getUsername(), user.getPassword())) {
            model.addAttribute("success", true);
            return "loginPage";
        } else {
            model.addAttribute("error", "Wrong login and/or password.");
            return "loginPage";
        }
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

}
