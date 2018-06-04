package com.makadown.springboot.app.models.dao;

//JpaRepository extiende de CRUDRepository y también se puede paginar
import org.springframework.data.jpa.repository.JpaRepository;

import com.makadown.springboot.app.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	/**
	 * A través del nombre del método (Query method name), se ejecutará
	 * la consulta JPQL:<br/>
	 * <center><i>"Select u from Usuario u where u.username=?1"</i></center>
	 * */
	public Usuario findByUsername(String username);


}
