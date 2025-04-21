package com.example.steps_tdee_calculator.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Service
public class TdeeService {

    public double calculateTdee(String gender, double weight, double height, int age) {
        // Mifflin St Jeor
        //Men 	BMR = (10 × weight in kg) + (6.25 × height in cm) – (5 × age in years) + 5
        //Women 	BMR = (10 × weight in kg) + (6.25 × height in cm) – (5 × age in years) – 161
        if (Objects.equals(gender, "male")) {
            return (10*weight) + (6.25*height) - (5*age) + 5;
        }
        return (10*weight) + (6.25*height) - (5*age) - 161;
    }

}
