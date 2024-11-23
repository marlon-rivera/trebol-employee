package com.trebol.auth.adapters.driving.http.mapper;

import com.trebol.auth.adapters.driving.http.dto.request.EmployeeRequest;
import com.trebol.auth.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEmployeeRequestMapper {

    Employee toEmployee(EmployeeRequest source);

}
