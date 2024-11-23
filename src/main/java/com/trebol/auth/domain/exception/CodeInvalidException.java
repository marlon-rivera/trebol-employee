package com.trebol.auth.domain.exception;

public class CodeInvalidException extends RuntimeException {
    public CodeInvalidException() {
        super("El codigo ingresado es invalido.");
    }
}
