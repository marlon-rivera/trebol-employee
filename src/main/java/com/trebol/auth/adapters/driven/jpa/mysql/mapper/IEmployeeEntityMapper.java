package com.trebol.auth.adapters.driven.jpa.mysql.mapper;

import com.trebol.auth.adapters.driven.jpa.mysql.entity.EmployeeEntity;
import com.trebol.auth.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IEmployeeEntityMapper {

    EmployeeEntity toEntity(Employee employee);

    Employee toEmployee(EmployeeEntity employeeEntity);

    default Optional<Employee> toEmployeeOptional(Optional<EmployeeEntity> employeeEntityOp){
        return employeeEntityOp.map(this::toEmployee);
    }

    List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntities);
}
