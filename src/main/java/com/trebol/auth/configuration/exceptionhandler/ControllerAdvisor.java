package com.trebol.auth.configuration.exceptionhandler;

import com.trebol.auth.domain.exception.CodeInvalidException;
import com.trebol.auth.domain.exception.JwtExpiredException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> {
                    if (objectError instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    } else {
                        return objectError.getDefaultMessage();
                    }
                })
                .toList();

        return ResponseEntity.badRequest().body(
                new ValidationExceptionResponse(errorMessages, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorsMessages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        return ResponseEntity.badRequest().body(
                new ValidationExceptionResponse(errorsMessages, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(JwtExpiredException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredJwtException(JwtExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionResponse("Sesión expirada, ingrese nuevamente.", HttpStatus.UNAUTHORIZED.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(CodeInvalidException.class)
    public ResponseEntity<ExceptionResponse> handleCodeInvalidException(CodeInvalidException ex) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now())
        );
    }
}