package com.example.steps_tdee_calculator.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionPageController {

    @GetMapping("/forbidden")
    public String forbiddenPage() {
        return "forbidden";
    }

}
