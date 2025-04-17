package com.example.steps_tdee_calculator.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDto {
    private String username;
    private List<TdeeDto> tdeeList;
    private double bmr;
    private double height;
    private List<WeightDto> weightList;
    private int age;
    private String gender;
    private String role;
}
