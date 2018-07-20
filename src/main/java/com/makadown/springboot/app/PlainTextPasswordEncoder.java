package com.makadown.springboot.app;

import org.springframework.security.crypto.password.PasswordEncoder;

/*  CLASE CUSTOMIZADA para usarse nomas para probar con password planos.
 *  
 *  Este es porque la clase NoOpPasswordEncoder de Spring esta deprecada, además de
 *  que Spring recomienda encarecidamente usar encriptadores (minimo BCrypt)
 *  Esta clase la usaré por el momento para las contrasenias planas de micampus o ECW / inscripciones
 *  */
public class PlainTextPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new PlainTextPasswordEncoder();

    PlainTextPasswordEncoder() {
    }
}