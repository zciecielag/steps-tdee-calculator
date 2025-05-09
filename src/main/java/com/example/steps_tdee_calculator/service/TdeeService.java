package com.example.steps_tdee_calculator.service;

import com.example.steps_tdee_calculator.dto.TdeeChartDTO;
import com.example.steps_tdee_calculator.entity.AppUser;
import com.example.steps_tdee_calculator.entity.Tdee;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Service
public class TdeeService {

    @Autowired
    private AppUserRepository appUserRepository;

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

    public TdeeChartDTO getTdeeChartData(Long userId) throws UserDoesNotExistException {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(UserDoesNotExistException::new);

        List<LocalDate> dates = new ArrayList<>();
        List<Double> tdeeValues = new ArrayList<>();
        List<Tdee> tdeeList = user.getTdeeList();

        for (Tdee tdee : tdeeList) {
            dates.add(tdee.getDateEntered());
            tdeeValues.add(tdee.getValue());
        }

        return new TdeeChartDTO(dates, tdeeValues);
    }

}
