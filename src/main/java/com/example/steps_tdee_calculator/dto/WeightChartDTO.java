package com.example.steps_tdee_calculator.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeightChartDTO {
    private List<LocalDate> dates;
    private List<Double> weightValues;
}
