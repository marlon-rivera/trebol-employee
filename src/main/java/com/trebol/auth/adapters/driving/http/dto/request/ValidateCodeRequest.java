package com.trebol.auth.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidateCodeRequest {

    @NotNull
    private String email;
    @NotNull
    private String code;
    @NotNull
    private String password;

}
