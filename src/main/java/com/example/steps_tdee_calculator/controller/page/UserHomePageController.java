package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.entity.user.AppUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHomePageController {

    @GetMapping("/userHomePage")
    public String userHomePage(Model model, HttpSession session) {
        System.out.println(session.getAttribute("user"));
        model.addAttribute("username", ((AppUser) session.getAttribute("user")).getUsername());
        model.addAttribute("currentTdee", ((AppUser) session.getAttribute("user")).getCurrentTdee());
        model.addAttribute("currentBmr", ((AppUser) session.getAttribute("user")).getCurrentBmr());
        model.addAttribute("height", ((AppUser) session.getAttribute("user")).getHeight());
        model.addAttribute("weight", ((AppUser) session.getAttribute("user")).getWeight());
        model.addAttribute("age", ((AppUser) session.getAttribute("user")).getAge());
        model.addAttribute("gender", ((AppUser) session.getAttribute("user")).getGender());
        return "userHomePage";
    }
}
