package com.trebol.auth.adapters.driven.jpa.mysql.adapter;

import com.trebol.auth.adapters.driven.jpa.mysql.mapper.IEmployeeEntityMapper;
import com.trebol.auth.adapters.driven.jpa.mysql.repository.IEmployeeRepository;
import com.trebol.auth.domain.model.Employee;
import com.trebol.auth.domain.model.Role;
import com.trebol.auth.domain.spi.IEmployeePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeAdapter implements IEmployeePersistencePort {

    private final IEmployeeRepository employeeRepo;
    private final IEmployeeEntityMapper mapper;

    @Override
    public void createEmployeeOrUpdate(Employee employee) {
        System.out.println(employee);
        employeeRepo.save(mapper.toEntity(employee));
    }


    @Override
    public void deleteEmployee(String id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Optional<Employee> getEmployeeByIdOrByEmail(String id, String email) {
        return mapper.toEmployeeOptional(employeeRepo.findByIdOrEmail(id, email));
    }

    @Override
    public List<Employee> getEmployees() {
        return mapper.toEmployeeList(employeeRepo.findAllByRole(Role.EMPLOYEE));
    }

    @Override
    public Optional<Employee> findEmployeeById(String id) {
        return mapper.toEmployeeOptional(employeeRepo.findById(id));
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return mapper.toEmployeeOptional(employeeRepo.findByEmail(email));
    }
}
