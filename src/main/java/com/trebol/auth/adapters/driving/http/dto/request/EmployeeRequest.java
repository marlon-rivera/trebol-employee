package com.trebol.auth.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRequest {

    @NotNull(message = "El número de identificación del empleado no puede estar vacio.")
    private String id;
    @NotNull(message = "El nombre del empleado no puede estar vacio.")
    private String name;
    @NotNull(message = "El apellido del empleado no puede estar vacio.")
    private String lastName;
    @NotNull(message = "El correo electronico del empleado no puede estar vacio.")
    private String email;
    @NotNull(message = "El numero de celular del empleado no puede estar vacio.")
    private String phone;
    @NotNull(message = "La dirección del empleado no puede estar vacio.")
    private String address;

}
