package com.trebol.auth.adapters.driven.jwt;


import com.trebol.auth.configuration.jwt.JwtService;
import com.trebol.auth.domain.spi.IJwtPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAdapter implements IJwtPort {

    private final JwtService jwtService;

    @Override
    public String getToken(String id, String role, String email) {
        return jwtService.getToken(id, role, email);
    }
}
