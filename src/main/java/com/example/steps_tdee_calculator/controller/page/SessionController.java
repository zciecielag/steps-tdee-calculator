package com.example.steps_tdee_calculator.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    @GetMapping("/sessionInvalid")
    public String invalidSession() {
        return "sessionInvalid";
    }

    @GetMapping("/sessionExpired")
    public String expiredSession() {
        return "sessionExpired";
    }
}
