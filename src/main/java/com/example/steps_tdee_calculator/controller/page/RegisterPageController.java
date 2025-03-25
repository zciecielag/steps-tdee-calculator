package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.dto.AppUserDto;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterPageController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute("newUser", new AppUserDto());
        return "registerPage";
    }

    @PostMapping("/registerForm")
    public String registerSubmit(@ModelAttribute AppUserDto newUser, Model model) {
        model.addAttribute("newUser", new AppUserDto());
        try {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userService.saveUser(newUser);
            model.addAttribute("successText", true);
        } catch (UsernameExistsException usernameExistsException) {
            model.addAttribute("errorText", "A user with this username already exists.");
        }
        return "registerPage";
    }
}
