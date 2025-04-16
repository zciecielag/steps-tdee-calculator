package com.example.steps_tdee_calculator.system;

import com.example.steps_tdee_calculator.exception.UserDoesNotExistException;
import com.example.steps_tdee_calculator.exception.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> handleUserNotFound(UserDoesNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user doesn't exist.");
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<String> handleUsernameExists(UsernameExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with that username already exists.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied.");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resource found.");
    }

}
