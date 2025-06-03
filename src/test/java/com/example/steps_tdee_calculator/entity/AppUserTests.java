package com.example.steps_tdee_calculator.entity;

import com.example.steps_tdee_calculator.dto.AppUserRegisterDto;
import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import com.example.steps_tdee_calculator.repository.AppUserRepository;
import com.example.steps_tdee_calculator.service.AppUserService;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class AppUserTests {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @Test
    public void testIfCanSaveAppUser() {
        AppUser appUser = new AppUser.UserBuilder()
                .setUsername("testUser")
                .setPassword("password123")
                .build();
        appUserRepository.save(appUser);
        assertTrue(appUserRepository.existsByUsername("testUser"));
    }

    @Test
    public void testIfSaveUserThrowsExceptionIfUsernameExists() throws UsernameExistsException {
        AppUserRegisterDto userDto = AppUserRegisterDto.builder()
                .username("testUser")
                .password("password123")
                .age(30)
                .height(180)
                .currentWeight(80)
                .build();

        appUserService.saveUser(userDto, 0);
        assertThrows(UsernameExistsException.class, () -> appUserService.saveUser(userDto, 0));
    }

    @Test
    public void testIfCanSaveAppUserWithData() throws UsernameExistsException {
        AppUserRegisterDto userDto = AppUserRegisterDto.builder()
                .username("testUser")
                .password("password123")
                .age(25)
                .height(175)
                .bmr(1500)
                .currentTdee(2000)
                .currentWeight(70)
                .build();

        appUserService.saveUser(userDto, 0);
        assertTrue(appUserRepository.existsByUsername("testUser"));
    }

    @Test
    public void testRemoveUser() throws UsernameExistsException, UserDoesNotExistException {
        AppUserRegisterDto userDto = AppUserRegisterDto.builder()
                .username("testUser")
                .password("password123")
                .age(25)
                .height(165)
                .currentWeight(60)
                .build();

        appUserService.saveUser(userDto, 0);
        var user = appUserRepository.findByUsername("testUser");

        appUserService.removeUser(user.getId());
        assertTrue(appUserRepository.findById(user.getId()).isEmpty());
    }

    @Test
    public void testIfCanUpdateUserWeight() throws UsernameExistsException, UserDoesNotExistException {
        AppUserRegisterDto userDto = AppUserRegisterDto.builder()
                .username("testUser")
                .password("password123")
                .age(28)
                .height(170)
                .currentWeight(75)
                .build();

        appUserService.saveUser(userDto, 0);
        var user = appUserRepository.findByUsername("testUser");

        appUserService.updateUserWeight(user.getId(), 77);
        var updatedUser = appUserRepository.findById(user.getId()).orElseThrow();

        assertTrue(updatedUser.getWeightList().size() > 1);
        assertTrue(updatedUser.getTdeeList().size() > 1);
    }

    @Test
    public void testIfCanUpdateUserTdeeWithSteps() throws Exception {
        AppUserRegisterDto userDto = AppUserRegisterDto.builder()
                .username("testUser")
                .password("password123")
                .age(28)
                .height(170)
                .currentWeight(75)
                .build();

        appUserService.saveUser(userDto, 0);
        var user = appUserRepository.findByUsername("testUser");

        appUserService.updateUserTdeeWithSteps(user.getId(), 8000, 1.2, 45);
        var updatedUser = appUserRepository.findById(user.getId()).orElseThrow();

        assertTrue(updatedUser.getTdeeList().size() > 1);
    }

}
