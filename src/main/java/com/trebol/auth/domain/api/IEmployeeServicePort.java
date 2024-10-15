package com.trebol.auth.domain.api;

import com.trebol.auth.domain.model.Auth;
import com.trebol.auth.domain.model.Employee;

import java.util.List;

public interface IEmployeeServicePort {

    void createEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(String id);
    List<Employee> getEmployees();
    Auth login(String email, String password);
}
