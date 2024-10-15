package com.trebol.auth.domain.spi;

public interface IEncoderPort {

    String encode(String text);
    boolean matches(String rawPassword, String encodedPassword);

}
