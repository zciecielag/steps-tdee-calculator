package com.example.steps_tdee_calculator.controller.entity;

import com.example.steps_tdee_calculator.dto.AppUserDto;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    AppUserService userService;

    @GetMapping("/getAll")
    public List<AppUserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody AppUserDto appUserDto) throws UsernameExistsException {
        userService.saveUser(appUserDto);
    }
    @GetMapping("/getById/{id}")
    public AppUserDto getUserById(@PathVariable Long id) throws UserDoesNotExistException {
        return userService.getUserById(id);
    }

    @DeleteMapping("/removeUser/{id}")
    public void removeUser(@PathVariable Long id) throws UserDoesNotExistException {
        userService.removeUser(id);
    }

}
