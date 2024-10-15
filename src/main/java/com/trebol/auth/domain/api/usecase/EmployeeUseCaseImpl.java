package com.trebol.auth.domain.api.usecase;

import com.trebol.auth.domain.api.IEmployeeServicePort;
import com.trebol.auth.domain.exception.EmployeeAlreadyExistsException;
import com.trebol.auth.domain.exception.EmployeeIncorrectPasswordException;
import com.trebol.auth.domain.exception.EmployeeNotFoundException;
import com.trebol.auth.domain.model.Auth;
import com.trebol.auth.domain.model.Employee;
import com.trebol.auth.domain.model.Role;
import com.trebol.auth.domain.spi.*;
import com.trebol.auth.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeUseCaseImpl implements IEmployeeServicePort {

    private final IEmployeePersistencePort persistencePort;
    private final IEncoderPort encoderPort;
    private final IAuthenticationPort authenticationPort;
    private final IJwtPort jwtPort;
    private final IEmailPort emailPort;
    private final SecureRandom random;

    @Override
    public void createEmployee(Employee employee) {
        Optional<Employee> employeeOptional = persistencePort.getEmployeeByIdOrByEmail(employee.getId(), employee.getEmail());
        if(employeeOptional.isPresent()) {
            throw new EmployeeAlreadyExistsException();
        }
        employee.setRole(Role.EMPLOYEE);
        String password = generatePassword();
        employee.setPassword(encoderPort.encode(password));
        emailPort.sendEmailPassword(employee.getEmail(), password, employee.getName() + " " + employee.getLastName());
        persistencePort.createEmployeeOrUpdate(employee);
    }

    @Override
    public void updateEmployee(Employee employeeUpdate) {
        Optional<Employee> employeeOp = persistencePort.findEmployeeById(employeeUpdate.getId());
        if(employeeOp.isEmpty()){
            throw new EmployeeNotFoundException();
        }
        Employee employee = employeeOp.get();
        if(employeeUpdate.getEmail() != null){
            employee.setEmail(employeeUpdate.getEmail());
        }
        if(employeeUpdate.getAddress() != null){
            employee.setAddress(employeeUpdate.getAddress());
        }
        if(employeeUpdate.getPhone() != null){
            employee.setPhone(employeeUpdate.getPhone());
        }
        persistencePort.createEmployeeOrUpdate(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = persistencePort.findEmployeeById(id).orElseThrow(EmployeeNotFoundException::new);
        persistencePort.deleteEmployee(employee.getId());
    }

    @Override
    public List<Employee> getEmployees() {
        return persistencePort.getEmployees();
    }

    @Override
    public Auth login(String email, String password) {
        Optional<Employee> employeeOptional = persistencePort.findEmployeeByEmail(email);
        if(employeeOptional.isEmpty()){
            throw new EmployeeIncorrectPasswordException();
        }
        Employee employee = employeeOptional.get();
        if(!encoderPort.matches(password, employee.getPassword())){
            throw new EmployeeIncorrectPasswordException();
        }
        authenticationPort.authenticate(employee.getId(), password);
        return new Auth(jwtPort.getToken(employee.getId(), employee.getRole().name(), employee.getEmail()));
    }

    private String generatePassword() {
        StringBuilder password = new StringBuilder(Constants.LENGTH_PASSWORD);

        for(int i = 0; i < Constants.LENGTH_PASSWORD; i++){
            int randomIndex = random.nextInt(Constants.CHARACTERS_PASSWORD.length());
            password.append(Constants.CHARACTERS_PASSWORD.charAt(randomIndex));
        }
        return password.toString();
    }
}