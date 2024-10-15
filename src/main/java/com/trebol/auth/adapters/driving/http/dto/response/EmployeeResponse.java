package com.trebol.auth.adapters.driving.http.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;

}
