package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.entity.user.AppUser;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginPageController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "loginPage";
    }

//    @PostMapping("/loginForm")
//    public String loginSubmit(@ModelAttribute AppUser appUser, Model model) {
//        model.addAttribute("user", appUser);
//        if (userService.verifyUser(appUser.getUsername(), appUser.getPassword())) {
//            model.addAttribute("success", true);
//            return "loginPage";
//        } else {
//            model.addAttribute("error", "Wrong login and/or password.");
//            return "loginPage";
//        }
//    }

    @ModelAttribute("user")
    public AppUser user() {
        return new AppUser();
    }

}
