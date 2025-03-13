package com.example.steps_tdee_calculator.service;

import com.example.steps_tdee_calculator.dto.UserDto;
import com.example.steps_tdee_calculator.entity.User;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean verifyUser(String username, String password) {
        var userFromDb = userRepository.findByUsername(username);
        if (userFromDb != null) {
            return userFromDb.getPassword().equals(password);
        }
        return false;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserDto getUserById(Long id) throws UserDoesNotExistException {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getPassword());
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDto(user.getId(), user.getUsername(), user.getName(), user.getSurname(),
                    user.getPassword()));
        }
        return result;
    }

    public void saveUser(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getName(), userDto.getSurname(),
                userDto.getPassword());
        userRepository.save(user);
    }

    public void removeUser(Long id) throws UserDoesNotExistException {
        var user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new UserDoesNotExistException();
        }
    }


}
