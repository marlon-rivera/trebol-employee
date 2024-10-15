package com.trebol.auth.adapters.driving.http.controller;

import com.trebol.auth.adapters.driving.http.dto.request.EmployeeRequest;
import com.trebol.auth.adapters.driving.http.dto.request.EmployeeUpdateRequest;
import com.trebol.auth.adapters.driving.http.dto.request.LoginRequest;
import com.trebol.auth.adapters.driving.http.dto.response.EmployeeResponse;
import com.trebol.auth.adapters.driving.http.mapper.IEmployeeRequestMapper;
import com.trebol.auth.adapters.driving.http.mapper.IEmployeeResponseMapper;
import com.trebol.auth.domain.api.IEmployeeServicePort;
import com.trebol.auth.domain.model.Auth;
import com.trebol.auth.domain.model.Employee;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeServicePort employeeService;
    private final IEmployeeRequestMapper requestMapper;
    private final IEmployeeResponseMapper responseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        employeeService.createEmployee(requestMapper.toEmployee(employeeRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmail(employeeUpdateRequest.getEmail());
        employee.setAddress(employeeUpdateRequest.getAddress());
        employee.setPhone(employeeUpdateRequest.getPhone());
        employeeService.updateEmployee(employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(responseMapper.toEmployeeResponses(employeeService.getEmployees()));
    }

    @PostMapping("/login")
    public ResponseEntity<Auth> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(employeeService.login(loginRequest.getEmail(), loginRequest.getPassword()));
    }
}
