package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.dto.UserDto;
import com.example.steps_tdee_calculator.entity.User;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterPageController {

    @Autowired
    private UserService userService;

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute("newUser", new User());
        return "registerPage";
    }

    @PostMapping("/registerForm")
    public String registerSubmit(@ModelAttribute User newUser, Model model) throws UsernameExistsException {
        model.addAttribute("newUser", newUser);
        if (!userService.existsByUsername(newUser.getUsername())) {
            userService.saveUser(new UserDto(newUser.getUsername(),
                    newUser.getName(), newUser.getSurname(), newUser.getPassword()));
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", "A user with this username already exists.");
        }
        return "registerPage";
    }
}
