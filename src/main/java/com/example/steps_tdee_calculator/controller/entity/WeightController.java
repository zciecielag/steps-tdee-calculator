package com.example.steps_tdee_calculator.controller.entity;

import com.example.steps_tdee_calculator.dto.WeightDto;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/weights")
public class WeightController {

    @Autowired
    private WeightService weightService;

    @GetMapping("/getAll")
    public List<WeightDto> getAllWeights() {
        return weightService.getAllWeights();
    }

    @GetMapping("/getByUserId/{id}")
    public List<WeightDto> getWeightsByUserId(@PathVariable Long id) throws UserDoesNotExistException {
        return weightService.getWeightsByUserId(id);
    }
}
