package com.example.steps_tdee_calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A user with that username already exists.")
public class UsernameExistsException extends Exception{
}
