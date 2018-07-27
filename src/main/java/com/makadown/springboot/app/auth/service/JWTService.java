package com.makadown.springboot.app.auth.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;

/**
 * Esta interfaz me ayudara a trabajar con distintos proveedores de JWT
 * Provee un diseño genérico para cualquier implementación.
 * 
 * Aqui establecemos un diseño de comportamiento de cada clase concreta que implementará el token.
 * */
public interface JWTService {
	
	public String create(Authentication authResult) throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String getUsername(String token);
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException ;
	public String resolve(String token); 

}
