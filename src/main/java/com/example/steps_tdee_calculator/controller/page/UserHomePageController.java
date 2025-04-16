package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.dto.SessionAppUserDto;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserHomePageController {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/userHomePage")
    public String userHomePage(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            SessionAppUserDto appUser = appUserRepository.findUserDetailsByUsername(username);

            if (appUser != null) {
                session.setAttribute("user", appUser);

                model.addAttribute("username", appUser.getUsername());
                //model.addAttribute("tdee", (appUser.getTdeeList()).get(appUser.getTdeeList().size()-1));
                model.addAttribute("currentBmr", appUser.getBmr());
                model.addAttribute("height", appUser.getHeight());
                //model.addAttribute("weight", (appUser.getWeightList()).get(appUser.getWeightList().size()-1));
                model.addAttribute("age", appUser.getAge());
                model.addAttribute("gender", appUser.getGender());
            }
        }

        return "userHomePage";
    }
}
