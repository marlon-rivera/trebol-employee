package com.trebol.auth.utils;

public class Constants {

    private Constants(){}

    public static final String HEADER_AUTH = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static int LENGTH_PASSWORD = 12;
    public static final String CHARACTERS_PASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+[]{}|;:,.<>?";
    public static final String EMPLOYEE_ALREADY_EXISTS_EXCEPTION = "Un empleado con el mismo correo o número de identifiación ya esta registrado.";
    public static final String EMPLOYEE_NOT_FOUND_EXCEPTION = "El empleado solicitado no se encuentra registrado en la base de datos.";
    public static final String EMAIL = "email";
    public static final String ROLE_PREFIX = "ROLE";
    public static final String ROLE_SECURITY_PREFIX = "ROLE_";
    public static final String USER_EMAIL_OR_PASSWORD_INCORRECT = "El correo electronico o la contraseña son incorrectas";
    public static final String PASSWORD_USER_BLANK = "La contraseña no puede estar vacia.";
    public static final String EMAIL_NOT_VALID = "El correo ingresado no es valido.";



}
