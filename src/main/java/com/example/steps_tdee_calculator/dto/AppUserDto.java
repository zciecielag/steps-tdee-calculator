package com.example.steps_tdee_calculator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDto {
    private String username;
    private String password;
    private double currentTdee;
    private double currentBmr;
    private double height;
    private double weight;
    private int age;
    private boolean gender;
    private String role;
}
