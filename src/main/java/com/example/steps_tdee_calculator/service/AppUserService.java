package com.example.steps_tdee_calculator.service;

import com.example.steps_tdee_calculator.dto.AppUserDto;
import com.example.steps_tdee_calculator.entity.user.AppUser;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public boolean verifyUser(String username, String password) {
        var userFromDb = appUserRepository.findByUsername(username);
        if (userFromDb != null) {
            return userFromDb.getPassword().equals(password);
        }
        return false;
    }

    public boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
    }

    public AppUserDto getUserById(Long id) throws UserDoesNotExistException {
        var user = appUserRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return AppUserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .currentTdee(user.getCurrentTdee())
                .currentBmr(user.getCurrentBmr())
                .height(user.getHeight())
                .weight(user.getWeight())
                .age(user.getAge())
                .gender(user.getGender())
                .role(user.getRole()).build();
    }

    public List<AppUserDto> getAllUsers() {
        var users = appUserRepository.findAll();
        List<AppUserDto> result = new ArrayList<>();
        for (AppUser user : users) {
            result.add(AppUserDto.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .currentTdee(user.getCurrentTdee())
                    .currentBmr(user.getCurrentBmr())
                    .height(user.getHeight())
                    .weight(user.getWeight())
                    .age(user.getAge())
                    .gender(user.getGender())
                    .role(user.getRole()).build());
        }
        return result;
    }

    public void saveUser(AppUserDto appUserDto) throws UsernameExistsException {
        if (existsByUsername(appUserDto.getUsername())) {
            throw new UsernameExistsException();
        } else {
            AppUser appUser = new AppUser.UserBuilder()
                    .setUsername(appUserDto.getUsername())
                    .setPassword(appUserDto.getPassword())
                    .build();
            appUserRepository.save(appUser);
        }
    }

    public void removeUser(Long id) throws UserDoesNotExistException {
        var user = appUserRepository.findById(id).orElse(null);
        if (user != null) {
            appUserRepository.delete(user);
        } else {
            throw new UserDoesNotExistException();
        }
    }


}
