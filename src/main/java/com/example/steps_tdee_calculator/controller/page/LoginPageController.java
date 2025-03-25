package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.entity.user.AppUser;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPageController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/loginForm")
    public String loginForm(Model model, @RequestParam(value="success", required = false) String success) {
        if (success != null) {
            if (success.equals("true")) {
                model.addAttribute("successText", true);
            }
            if (success.equals("false")) {
                model.addAttribute("errorText", "Wrong login and/or password.");
            }
        }
        model.addAttribute("user", new AppUser());
        return "loginPage";
    }

    @ModelAttribute("user")
    public AppUser user() {
        return new AppUser();
    }

}
