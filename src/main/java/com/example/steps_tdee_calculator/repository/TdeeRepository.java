package com.example.steps_tdee_calculator.repository;

import com.example.steps_tdee_calculator.entity.Tdee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TdeeRepository extends JpaRepository<Tdee, Long> {
}
