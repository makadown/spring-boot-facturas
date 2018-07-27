package com.makadown.springboot.app.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makadown.springboot.app.auth.service.JWTService;
import com.makadown.springboot.app.auth.service.JWTServiceImpl;
import com.makadown.springboot.app.models.entity.Usuario;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private AuthenticationManager authenticationManager;
	
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {		
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		/* Si quisiera que mi API de login sea "/api/login" en lugar del predeterminado "/login" */
		setRequiresAuthenticationRequestMatcher( new AntPathRequestMatcher("/api/login", "POST") );
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		
		// logger.info("Username:  " + username + "    -   Password:  " + password );
		if (username.equals("") && password.equals("")) {
			/* Reviso si lo estoy recibiendo por RAW Json */
			Usuario user = null;
			try {
				user = new ObjectMapper().readValue( request.getInputStream(), Usuario.class);
				username = user.getUsername();
				password = user.getPassword();
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}		

		username = username.trim();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String token = jwtService.create(authResult);
		
		// Guardamos token generado en parametro Authorization
		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token); // ES IMPORTANTE que tenga al inicio el string "Bearer " con todo y espacio!
		
		// Creado un map que se transformará en contenido json
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("mensaje", String.format( "Hola %s, has sido autenticado exitosamente papu!", authResult.getName() ));
		
		// convierte el body a json
		response.getWriter().write( new ObjectMapper().writeValueAsString(body) );
		// indicar status de la peticion
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("mensaje", "Error de autenticación: credenciales incorrectas");
		body.put("error", failed.getMessage());
		// convierte el body a json
		response.getWriter().write( new ObjectMapper().writeValueAsString(body) );
		response.setStatus(401); // Unauthorized
		response.setContentType("application/json");
		
	}
	
}





















