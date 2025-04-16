package com.example.steps_tdee_calculator.repository;


import com.example.steps_tdee_calculator.dto.SessionAppUserDto;
import com.example.steps_tdee_calculator.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT new com.example.steps_tdee_calculator.dto.SessionAppUserDto(u.username, u.bmr, u.height, u.age, u.gender, u.role) " +
            "FROM AppUser u WHERE u.username = :username")
    SessionAppUserDto findUserDetailsByUsername(@Param("username") String username);

}
