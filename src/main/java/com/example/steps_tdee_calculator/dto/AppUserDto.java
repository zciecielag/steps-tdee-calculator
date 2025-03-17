package com.example.steps_tdee_calculator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDto {
    private String username;
    private String name;
    private String surname;
    private String password;
    private String role;
}
