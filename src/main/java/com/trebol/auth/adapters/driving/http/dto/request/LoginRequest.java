package com.trebol.auth.adapters.driving.http.dto.request;

import com.trebol.auth.utils.Constants;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @NotEmpty(message = Constants.EMAIL_NOT_VALID)
    private String email;
    @NotEmpty(message = Constants.PASSWORD_USER_BLANK)
    private String password;

}
