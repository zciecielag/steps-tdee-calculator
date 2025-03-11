package com.example.steps_tdee_calculator.controller.page;

//import com.example.steps_tdee_calculator.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterPageController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        //model.addAttribute("newUser", new User());
        return "registerPage";
    }
}
