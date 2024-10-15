package com.trebol.auth.configuration.exceptionhandler;

import com.trebol.auth.domain.exception.EmployeeAlreadyExistsException;
import com.trebol.auth.domain.exception.EmployeeIncorrectPasswordException;
import com.trebol.auth.domain.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmployeeControllerAdvisor {

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(EmployeeIncorrectPasswordException.class)
    public ResponseEntity<ExceptionResponse> handleEmployeeIncorrectPassword(EmployeeIncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString(), LocalDateTime.now())
        );
    }
}
