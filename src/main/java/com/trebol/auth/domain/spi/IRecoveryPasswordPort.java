package com.trebol.auth.domain.spi;

public interface IRecoveryPasswordPort {

    String generateRecoverCode(String email);
    boolean validateRecoverCode(String email, String recoverCode);

}
