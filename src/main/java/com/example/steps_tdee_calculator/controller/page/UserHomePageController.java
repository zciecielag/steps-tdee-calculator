package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.dto.SessionAppUserDto;
import com.example.steps_tdee_calculator.dto.TdeeDto;
import com.example.steps_tdee_calculator.dto.WeightDto;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.service.AppUserService;
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
            var appUser = appUserRepository.findByUsername(username);
            List<TdeeDto> tdeeList = appUser.getTdeeList()
                    .stream()
                    .map(tdee -> {
                        return TdeeDto.builder()
                                .value(tdee.getValue())
                                .dateEntered(tdee.getDateEntered())
                                .build();
                    })
                    .toList();
            List<WeightDto> weightList = appUser.getWeightList()
                    .stream()
                    .map(weight -> {
                        return WeightDto.builder()
                                .value(weight.getValue())
                                .dateEntered(weight.getDateEntered())
                                .build();
                    })
                    .toList();
            session.setAttribute("user", appUser);
            model.addAttribute("username", appUser.getUsername());
            model.addAttribute("tdee", tdeeList.get(tdeeList.size()-1).getValue());
            model.addAttribute("currentBmr", appUser.getBmr());
            model.addAttribute("height", appUser.getHeight());
            model.addAttribute("weight", weightList.get(weightList.size()-1).getValue());
            model.addAttribute("age", appUser.getAge());
            model.addAttribute("gender", appUser.getGender());
        }

        return "userHomePage";
    }
}
