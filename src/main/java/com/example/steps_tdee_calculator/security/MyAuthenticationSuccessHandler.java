package com.example.steps_tdee_calculator.security;

import com.example.steps_tdee_calculator.entity.user.AppUser;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HttpSession session;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        AppUser foundUser = appUserRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
        AppUser sessionUser = new AppUser.UserBuilder()
                .setUsername(foundUser.getUsername())
                .setCurrentTdee(foundUser.getCurrentTdee())
                .setCurrentBmr(foundUser.getCurrentBmr())
                .setHeight(foundUser.getHeight())
                .setWeight(foundUser.getWeight())
                .setAge(foundUser.getAge())
                .setGender(foundUser.isGender())
                .build();
        session.setAttribute("user", sessionUser);
        response.sendRedirect("/userHomePage" );

    }
}
