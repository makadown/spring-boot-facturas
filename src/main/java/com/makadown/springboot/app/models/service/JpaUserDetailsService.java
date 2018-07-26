package com.makadown.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleInfoNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makadown.springboot.app.models.dao.IUsuarioDao;
import com.makadown.springboot.app.models.entity.Role;
import com.makadown.springboot.app.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
		
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		Usuario usuario = usuarioDao.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if ( usuario == null )
		{
			logger.error("Error login: credenciales inválidas para '"+username+"'.");
			throw new UsernameNotFoundException("Usuario '"+username+"' no existe en sistema");
		}
		
		for(Role role : usuario.getRoles())
		{
			//logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if ( authorities.isEmpty() )
		{
			logger.error("Error login: '"+username+"' no tiene asignado roles.");
			throw new UsernameNotFoundException("Usuario '"+username+"' no tiene roles asignados.");
		}
 		                                                           // accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return new User(username, usuario.getPassword(), usuario.getEnabled(), true,            true,               true,         authorities);
	}

}
