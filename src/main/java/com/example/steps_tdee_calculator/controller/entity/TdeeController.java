package com.example.steps_tdee_calculator.controller.entity;

import com.example.steps_tdee_calculator.dto.TdeeDto;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.service.TdeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tdees")
public class TdeeController {

    @Autowired
    private TdeeService tdeeService;

    @GetMapping("/getAll")
    public List<TdeeDto> getAllTdees() {
        return tdeeService.getAllTdees();
    }

    @GetMapping("/getByUserId/{id}")
    public List<TdeeDto> getTdeesByUserId(@PathVariable Long id) throws UserDoesNotExistException {
        return tdeeService.getTdeesByUserId(id);
    }
}
