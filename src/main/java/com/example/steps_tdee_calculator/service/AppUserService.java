package com.example.steps_tdee_calculator.service;

import com.example.steps_tdee_calculator.dto.AppUserDto;
import com.example.steps_tdee_calculator.dto.AppUserRegisterDto;
import com.example.steps_tdee_calculator.dto.TdeeDto;
import com.example.steps_tdee_calculator.dto.WeightDto;
import com.example.steps_tdee_calculator.entity.AppUser;
import com.example.steps_tdee_calculator.entity.Tdee;
import com.example.steps_tdee_calculator.entity.Weight;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.repository.TdeeRepository;
import com.example.steps_tdee_calculator.repository.WeightRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private TdeeRepository tdeeRepository;
    @Autowired
    private WeightRepository weightRepository;

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

    public AppUserDto getUserByUsername(String username) throws UserDoesNotExistException {
        var user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return AppUserDto.builder()
                .username(user.getUsername())
                .bmr(user.getBmr())
                .height(user.getHeight())
                .age(user.getAge())
                .gender(user.getGender())
                .role(user.getRole()).build();
    }

    public AppUserDto getUserById(Long id) throws UserDoesNotExistException {
        var user = appUserRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserDoesNotExistException();
        }
        return retrieveUserWithTdeeAndWeights(user);
    }

    public List<AppUserDto> getAllUsers() {
        var users = appUserRepository.findAll();
        List<AppUserDto> result = new ArrayList<>();
        for (AppUser user : users) {
            result.add(retrieveUserWithTdeeAndWeights(user));
        }
        return result;
    }

    public void saveUser(AppUserRegisterDto appUserDto) throws UsernameExistsException {
        if (existsByUsername(appUserDto.getUsername())) {
            throw new UsernameExistsException();
        } else {
            LocalDate currentDate = LocalDate.now();
            Tdee userTdee = new Tdee.TdeeBuilder()
                    .setValue(appUserDto.getCurrentTdee())
                    .setDateEntered(currentDate)
                    .build();
            Weight userWeight = new Weight.WeightBuilder()
                    .setValue(appUserDto.getCurrentWeight())
                    .setDateEntered(currentDate)
                    .build();
            AppUser appUser = new AppUser.UserBuilder()
                    .setUsername(appUserDto.getUsername())
                    .setPassword(appUserDto.getPassword())
                    .setTdeeList(new ArrayList<Tdee>(Arrays.asList(userTdee)))
                    .setBmr(appUserDto.getBmr())
                    .setHeight(appUserDto.getHeight())
                    .setWeightList(new ArrayList<Weight>(Arrays.asList(userWeight)))
                    .setAge(appUserDto.getAge())
                    .setGender(appUserDto.getGender())
                    .build();
            appUserRepository.save(appUser);
            userTdee.setUser(appUser);
            tdeeRepository.save(userTdee);
            userWeight.setUser(appUser);
            weightRepository.save(userWeight);
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

    private AppUserDto retrieveUserWithTdeeAndWeights(AppUser user) {
        List<TdeeDto> tdeeList = user.getTdeeList()
                .stream()
                .map(tdee -> {
                    return TdeeDto.builder()
                            .value(tdee.getValue())
                            .dateEntered(tdee.getDateEntered())
                            .build();
                })
                .toList();
        List<WeightDto> weightList = user.getWeightList()
                .stream()
                .map(weight -> {
                    return WeightDto.builder()
                            .value(weight.getValue())
                            .dateEntered(weight.getDateEntered())
                            .build();
                })
                .toList();
        return AppUserDto.builder()
                .username(user.getUsername())
                .tdeeList(tdeeList)
                .bmr(user.getBmr())
                .height(user.getHeight())
                .weightList(weightList)
                .age(user.getAge())
                .gender(user.getGender())
                .role(user.getRole()).build();
    }


}
