package com.trebol.auth.domain.exception;

import com.trebol.auth.utils.Constants;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super(Constants.EMPLOYEE_NOT_FOUND_EXCEPTION);
    }
}
