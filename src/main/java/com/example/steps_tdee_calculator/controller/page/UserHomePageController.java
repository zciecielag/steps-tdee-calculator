package com.example.steps_tdee_calculator.controller.page;

import com.example.steps_tdee_calculator.dto.TdeeChartDTO;
import com.example.steps_tdee_calculator.dto.TdeeDto;
import com.example.steps_tdee_calculator.dto.WeightChartDTO;
import com.example.steps_tdee_calculator.dto.WeightDto;
import com.example.steps_tdee_calculator.entity.AppUser;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.service.AppUserService;
import com.example.steps_tdee_calculator.service.TdeeService;
import com.example.steps_tdee_calculator.service.WeightService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/userHomePage")
public class UserHomePageController {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private TdeeService tdeeService;
    @Autowired
    private WeightService weightService;

    @GetMapping
    public String userHomePage(Model model, HttpSession session) throws UserDoesNotExistException {
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
                                .kcalFromSteps(tdee.getKcalFromSteps())
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
            model.addAttribute("tdee", (int) tdeeList.get(tdeeList.size()-1).getValue());
            model.addAttribute("bmr", (int) appUser.getBmr());
            model.addAttribute("height", (int) appUser.getHeight());
            model.addAttribute("weight", weightList.get(weightList.size()-1).getValue());
            model.addAttribute("age", appUser.getAge());
            model.addAttribute("gender", appUser.getGender());

            TdeeChartDTO tdeeChartData = tdeeService.getTdeeChartData(appUser.getId());
            WeightChartDTO weightChartDTO = weightService.getWeightChartData(appUser.getId());

            model.addAttribute("datesTdeeData", tdeeChartData.getDates());
            model.addAttribute("tdeesData", tdeeChartData.getTdeeValues());
            model.addAttribute("datesWeightData", weightChartDTO.getDates());
            model.addAttribute("weightsData", weightChartDTO.getWeightValues());
        }
        return "userHomePage";
    }

    @PostMapping("/addWeight")
    public String addWeight(Model model, @RequestParam double weight, HttpSession session) throws UserDoesNotExistException {
        var appUser = (AppUser) session.getAttribute("user");
        appUserService.updateUserWeight(appUser.getId(), weight);
        return "redirect:/userHomePage";
    }

    @PostMapping("/adjustSteps")
    public String adjustSteps(Model model, @RequestParam int steps, @RequestParam double pace, @RequestParam int time, HttpSession session) throws UserDoesNotExistException {
        var appUser = (AppUser) session.getAttribute("user");
        appUserService.updateUserTdeeWithSteps(appUser.getId(), steps, pace, time);
        return "redirect:/userHomePage";
    }
}
