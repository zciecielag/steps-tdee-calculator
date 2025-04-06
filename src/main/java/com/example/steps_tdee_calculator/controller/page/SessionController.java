package com.example.steps_tdee_calculator.controller.page;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    @GetMapping("/sessionExpired")
    public String expiredSession() {
        return "sessionExpired";
    }

    @GetMapping("/test-auth")
    public void testAuth(Authentication authentication) {
        if (authentication != null) {
            System.out.println("Username: " + authentication.getName());
            System.out.println("Authorities: " + authentication.getAuthorities());
        }
        System.out.println("Not authenticated");
    }
}
