package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.dto.AppUserDto;
import com.example.steps_tdee_calculator.dto.AppUserRegisterDto;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.service.AppUserService;
import com.example.steps_tdee_calculator.service.TdeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegisterPageController {

    @Autowired
    private AppUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TdeeService tdeeService;

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute("newUser", new AppUserRegisterDto());
        return "registerPage";
    }

    @PostMapping("/registerForm")
    public String registerSubmit(@ModelAttribute AppUserRegisterDto newUser, Model model,
                                 @RequestParam int steps, @RequestParam double pace, @RequestParam int time) {
        model.addAttribute("newUser", new AppUserRegisterDto());
        try {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            double kcalFromSteps = tdeeService.calculateKcalFromSteps(steps, pace, time, newUser.getCurrentWeight());
            userService.saveUser(newUser, Optional.of(kcalFromSteps));
            model.addAttribute("successText", true);
        } catch (UsernameExistsException usernameExistsException) {
            model.addAttribute("errorText", "A user with this username already exists.");
        }
        return "registerPage";
    }
}
