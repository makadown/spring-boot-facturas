package com.makadown.springboot.app.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.persistence.*;
import javax.validation.constraints.*; 

/**
 * @author Mario Serrano
 */
@Entity
@Table(name="facturas")
public class Factura implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3084551343029051034L;
 
    public Factura() {
		this.items = new ArrayList<ItemFactura>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; 
	
	@NotEmpty
    private String descripcion; 
    private String observacion; 
         
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)	
    private Date createAt; 
	
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
    
	@ManyToOne(fetch=FetchType.LAZY)
    private Cliente cliente;   
		
	/* Con JoinColumn, seindica que la relación es en 1 solo sentido. Factura con ItemFactura 
	 * No puedo crear ItemFactura de la nada. Tengo que tener creado primero una Factura. 
	 * */	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)	
	@JoinColumn(name="factura_id")
	private List<ItemFactura> items;
	/* A diferencia de Cliente, que su existencia puede ser independiente, su relación puede ser en ambos sentidos */

    public List<ItemFactura> getItems() {
		return items;
	}


	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public void addItemFactura(ItemFactura item)
	{
		this.items.add(item);
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Long getId() {
		return Id;
	}


	/**
     * @return
     */
    public Double getTotal() {
        Double total = 0.0;
        
        int size = items.size();
        
        for(int i=0; i< size ; i++)
        {
        	total += items.get(i).calcularImporte();
        }
        return total;
    }

}