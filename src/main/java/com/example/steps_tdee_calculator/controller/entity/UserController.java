package com.example.steps_tdee_calculator.controller.entity;

import com.example.steps_tdee_calculator.dto.UserDto;
import com.example.steps_tdee_calculator.entity.user.User;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public void saveUser(@RequestBody UserDto userDto) throws UsernameExistsException {
        userService.saveUser(userDto);
    }
    @GetMapping("/getById/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserDoesNotExistException {
        return userService.getUserById(id);
    }

    @DeleteMapping("/removeUser/{id}")
    public void removeUser(@PathVariable Long id) throws UserDoesNotExistException {
        userService.removeUser(id);
    }

}
