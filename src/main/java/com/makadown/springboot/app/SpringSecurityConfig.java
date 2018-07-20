package com.makadown.springboot.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.makadown.springboot.app.auth.handler.LoginSuccessHandler;
import com.makadown.springboot.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private AccessDeniedHandler accessDeniedHandler; 
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		// todo sobre /src/main/resources/static
		http.authorizeRequests()
		    .antMatchers("/", "/css/**", "/js/**", "/images/**",
		    		     "/listar-rest", "/api/clientes/**",
		    		     "/listar", "/locale").permitAll() 
				.antMatchers("/uploads/**").hasAnyRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().successHandler(successHandler).loginPage("/login").permitAll()
			.and()
			.logout().permitAll().and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		/* Deshabilitamos la proteccion csrf porque usaremos json web tokens 
		 * csrf se usa mas para formularios.
		 * */
		
		// los accesos estan configurados con anotaciones. Checar las clases.
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{		 
		
		build.userDetailsService(userDetailsService)
		 	  .passwordEncoder(passwordEncoder);
		 	  
	}

}
