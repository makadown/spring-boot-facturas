package com.makadown.springboot.app.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");

		/*Esto se va a ejecutar en toda request. En toda petición en la que exista Authorization en la cabecera	*/
		if ( !requiresAuthentication(header) ) {
			chain.doFilter(request, response);
			return;
		}
		
	}
	
	protected boolean requiresAuthentication(String header) {
		if (header == null || !header.startsWith("Bearer ")) {			
			return false;
		}
		return true;
	}

}





















