package com.example.steps_tdee_calculator.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value={"/", "/home"})
    public String homePage() {
        return "homePage";
    }
}
