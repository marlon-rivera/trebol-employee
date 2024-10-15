package com.trebol.auth.domain.spi;

public interface IJwtPort {

    String getToken(String id, String role, String email);

}
