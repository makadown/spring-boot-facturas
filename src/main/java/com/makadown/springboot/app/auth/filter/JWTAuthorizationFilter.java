package com.makadown.springboot.app.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makadown.springboot.app.auth.SimpleGrantedAuthorityMixin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");

		/*
		 * Esto se va a ejecutar en toda request. En toda petición en la que exista
		 * Authorization en la cabecera
		 */
		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		boolean tokenValido = false;
		Claims token = null;

		try {
			token = Jwts.parser().setSigningKey("mi.clave.secreta.papu".getBytes())
								.parseClaimsJws(header.replace("Bearer ", "")).getBody();
			tokenValido = true;
		} catch (JwtException | IllegalArgumentException e) {
			tokenValido = false;
			log.error(e.getMessage());
		}
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		if (tokenValido) {
			String username = token.getSubject();
			Object roles = token.get("authorities");
			
			Collection<? extends GrantedAuthority> authorities = Arrays.asList( 
					new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
					                  .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class ) );
			
			authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		}
		
		/* Esto autentica al usuario dentro de la petición. 
		 * 
		 * OJO: No estamos usando sesiones. Estamos autenticando por peticion por token JWT, NO por BD */ 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	protected boolean requiresAuthentication(String header) {
		if (header == null || !header.startsWith("Bearer ")) {
			return false;
		}
		return true;
	}

}
