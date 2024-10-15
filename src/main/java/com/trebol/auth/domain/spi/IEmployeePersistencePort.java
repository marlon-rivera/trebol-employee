package com.trebol.auth.domain.spi;

import com.trebol.auth.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeePersistencePort {

    void createEmployeeOrUpdate(Employee employee);
    void deleteEmployee(String id);
    Optional<Employee> getEmployeeByIdOrByEmail(String id, String email);
    List<Employee> getEmployees();
    Optional<Employee> findEmployeeById(String id);
    Optional<Employee> findEmployeeByEmail(String email);
}
