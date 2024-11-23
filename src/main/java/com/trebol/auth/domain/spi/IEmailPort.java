package com.trebol.auth.domain.spi;

public interface IEmailPort {

    void sendEmailPassword(String to, String password, String name);
    void sendEmailRecoveryPassword(String to, String code, String name);

}
