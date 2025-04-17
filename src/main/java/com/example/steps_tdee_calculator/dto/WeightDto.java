package com.example.steps_tdee_calculator.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeightDto {
    private double value;
    private LocalDate dateEntered;
}
