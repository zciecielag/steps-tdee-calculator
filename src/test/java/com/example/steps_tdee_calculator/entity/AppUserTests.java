package com.example.steps_tdee_calculator.entity;

import com.example.steps_tdee_calculator.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@Rollback(value = true)
public class AppUserTests {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    
}
