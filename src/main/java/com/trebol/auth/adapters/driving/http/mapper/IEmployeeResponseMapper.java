package com.trebol.auth.adapters.driving.http.mapper;

import com.trebol.auth.adapters.driving.http.dto.response.EmployeeResponse;
import com.trebol.auth.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmployeeResponseMapper {

    EmployeeResponse toEmployeeResponse(Employee employee);

    List<EmployeeResponse> toEmployeeResponses(List<Employee> employees);


}
