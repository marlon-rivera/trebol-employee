package com.trebol.auth.domain.exception;

import com.trebol.auth.utils.Constants;

public class EmployeeIncorrectPasswordException extends RuntimeException {
    public EmployeeIncorrectPasswordException() {
        super(Constants.USER_EMAIL_OR_PASSWORD_INCORRECT);
    }
}
