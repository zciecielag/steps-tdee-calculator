package com.example.steps_tdee_calculator.service;

import com.example.steps_tdee_calculator.dto.WeightChartDTO;
import com.example.steps_tdee_calculator.dto.WeightDto;
import com.example.steps_tdee_calculator.entity.AppUser;
import com.example.steps_tdee_calculator.entity.Weight;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.repository.WeightRepository;
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
    @Autowired
    private WeightRepository weightRepository;

    public List<WeightDto> getAllWeights() {
        var weights = weightRepository.findAll();
        List<WeightDto> result = new ArrayList<>();
        for (Weight weight : weights) {
            result.add(WeightDto.builder()
                    .value(weight.getValue())
                    .dateEntered(weight.getDateEntered())
                    .build());
        }
        return result;
    }

    public List<WeightDto> getWeightsByUserId(Long userId) throws UserDoesNotExistException {
        var user = appUserRepository.findById(userId)
                .orElseThrow(UserDoesNotExistException::new);
        List<WeightDto> result = new ArrayList<>();
        for (Weight weight : user.getWeightList()) {
            result.add(WeightDto.builder()
                    .value(weight.getValue())
                    .dateEntered(weight.getDateEntered())
                    .build());
        }
        return result;
    }

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
