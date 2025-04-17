package com.example.steps_tdee_calculator.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TdeeDto {
    private double value;
    private LocalDate dateEntered;
}
