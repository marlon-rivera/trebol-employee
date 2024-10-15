package com.trebol.auth.adapters.driven.jwt;

import com.trebol.auth.domain.spi.IAuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthenticationAdapter implements IAuthenticationPort {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
