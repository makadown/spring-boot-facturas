package com.makadown.springboot.app.models.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*; 

/*
 * OJO:
 * https://stackoverflow.com/questions/48986091/hibernate-notempty-is-deprecated
 * */

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	public Cliente() {
	  facturas = new ArrayList<Factura>();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)	
	private Date createAt;
	
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	
	private String foto;
	
	/*
	 * Aquí la relación es en ambos sentidos. Puedo crear un cliente sin tener aún asignada una factura  
	 * Al colocar el mapeo, se generará automáticamente la llave foránea en la tabla Facturas (cliente_id) de forma automática 
	 * */	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Factura> facturas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public void addFactura(Factura factura)
	{
		facturas.add(factura);
	}

	@Override
	public String toString() {
		return   nombre + " " + apellido ;
	}
}
