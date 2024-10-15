package com.trebol.auth.domain.exception;

import com.trebol.auth.domain.model.Employee;
import com.trebol.auth.utils.Constants;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException() {
        super(Constants.EMPLOYEE_ALREADY_EXISTS_EXCEPTION);
    }

}
