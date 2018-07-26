package com.makadown.springboot.app.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Esta clase es auxiliar para evitar un error en el JWTAuthorization filter donde no se 
 * puede instanciar de SimpleGrantedAuthority .
 * */
public abstract class SimpleGrantedAuthoritiesMixin {

	@JsonCreator
	public SimpleGrantedAuthoritiesMixin(@JsonProperty("authority") String role) {
	}

}
