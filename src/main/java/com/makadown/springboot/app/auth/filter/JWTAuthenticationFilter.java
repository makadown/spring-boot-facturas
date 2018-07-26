package com.makadown.springboot.app.auth.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makadown.springboot.app.models.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private AuthenticationManager authenticationManager;	

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
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
		
		// Recibimos el authResult que contiene los datos del usuario
		String username = ((User) authResult.getPrincipal() ).getUsername();
		long UnaHora = 3600000;
		
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		
		Claims claims = Jwts.claims();
		// agrego a los claims los roles en forma JSON
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles) );

		String token = 
				Jwts.builder().setClaims(claims)
				.setSubject(username).signWith( SignatureAlgorithm.HS512, "mi.clave.secreta.papu".getBytes() )
				.setIssuedAt(new Date())
				.setExpiration( new Date(  System.currentTimeMillis() + ( UnaHora *  4L )  ) )
				.compact();
		
		// Guardamos token generado en parametro Authorization
		response.addHeader("Authorization", "Bearer " + token); // ES IMPORTANTE que tenga al inicio el string "Bearer " con todo y espacio!
		
		// Creado un map que se transformará en contenido json
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("mensaje", String.format( "Hola %s, has sido autenticado exitosamente papu!", username ));
		
		// convierte el body a json
		response.getWriter().write( new ObjectMapper().writeValueAsString(body) );
		// indicar status de la peticion
		response.setStatus(200);
		response.setContentType("application/json");
	}
	
	

}
