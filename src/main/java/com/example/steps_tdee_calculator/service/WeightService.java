package com.example.steps_tdee_calculator.service;

import com.example.steps_tdee_calculator.dto.WeightChartDTO;
import com.example.steps_tdee_calculator.entity.AppUser;
import com.example.steps_tdee_calculator.entity.Weight;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class WeightService {

    @Autowired
    private AppUserRepository appUserRepository;

    public WeightChartDTO getWeightChartData(Long userId) throws UserDoesNotExistException {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(UserDoesNotExistException::new);

        List<LocalDate> dates = new ArrayList<>();
        List<Double> weightValues = new ArrayList<>();
        List<Weight> weightList = user.getWeightList();

        for (Weight weight: weightList) {
            dates.add(weight.getDateEntered());
            weightValues.add(weight.getValue());
        }

        return new WeightChartDTO(dates, weightValues);
    }
}
