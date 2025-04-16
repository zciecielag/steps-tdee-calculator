package com.example.steps_tdee_calculator.dto;

import com.example.steps_tdee_calculator.entity.Tdee;
import com.example.steps_tdee_calculator.entity.Weight;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRegisterDto {
    private String username;
    private String password;
    private double currentTdee;
    private double bmr;
    private double height;
    private double currentWeight;
    private int age;
    private String gender;
    private String role;
}
