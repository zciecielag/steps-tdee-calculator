package com.example.steps_tdee_calculator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionAppUserDto {
    private String username;
    private double bmr;
    private double height;
    private int age;
    private String gender;
    private String role;
}
