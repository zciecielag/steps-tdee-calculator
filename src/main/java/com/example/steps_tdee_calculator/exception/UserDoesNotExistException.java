package com.example.steps_tdee_calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="This user doesn't exist.")
public class UserDoesNotExistException extends Exception{
}
