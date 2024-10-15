package com.trebol.auth.domain.spi;

public interface IAuthenticationPort {

    void authenticate(String username, String password);

}
