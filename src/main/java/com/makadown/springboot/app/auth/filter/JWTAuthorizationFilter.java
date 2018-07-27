package com.makadown.springboot.app.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.makadown.springboot.app.auth.service.JWTService;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTService jwtService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			                      JWTService jwtService) {		
		super(authenticationManager);
		this.jwtService = jwtService;
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
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		if (jwtService.validate(header)) {
			
			authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), 
														null, jwtService.getRoles(header));
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
