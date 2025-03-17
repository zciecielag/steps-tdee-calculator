package com.example.steps_tdee_calculator.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String name;
    private String surname;
    private String password;
}
