package com.makadown.springboot.app.models.entity;

import java.util.*;

import javax.persistence.*;
 

/**
 * @author Mario Serrano
 */
@Entity
@Table(name="productos")
public class Producto implements java.io.Serializable {

	private static final long serialVersionUID = 3084551343029051034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Double precio;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)	
	private Date createAt;
	
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

}