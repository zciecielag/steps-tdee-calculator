package com.example.steps_tdee_calculator.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Service
public class TdeeService {

    public double calculateBmr(String gender, double weight, double height, int age) {
        // Mifflin St Jeor
        //Men 	BMR = (10 × weight in kg) + (6.25 × height in cm) – (5 × age in years) + 5
        //Women 	BMR = (10 × weight in kg) + (6.25 × height in cm) – (5 × age in years) – 161
        if (Objects.equals(gender, "male")) {
            return (10*weight) + (6.25*height) - (5*age) + 5;
        }
        double tdee = (10*weight) + (6.25*height) - (5*age) - 161;
        return Math.floor(tdee * 100) / 100;
    }

    public double calculateKcalFromSteps(int steps, double pace, int time, double weight) {
        // https://www.healthline.com/nutrition/10000-steps-calories-burned#estimating-calories-burned
        // 0.0175 * MET(pace) * weight * minutes
        if (pace == 3.2) {
            pace = 2.8;
        } else if (pace == 4.8) {
            pace = 4.3;
        } else if (pace == 6.4) {
            pace = 5.0;
        } else if (pace == 8.0) {
            pace = 8.3;
        } else {
            pace = 2.8;
        }
        double kcal = ((0.0175*pace*weight*time)/10000)*steps;
        return Math.floor(kcal * 100) / 100;
    }

    public double calculateTdee(String gender, double weight, double height, int age, double kcalFromSteps) {
        return calculateBmr(gender, weight, height, age) + kcalFromSteps;
    }

}
