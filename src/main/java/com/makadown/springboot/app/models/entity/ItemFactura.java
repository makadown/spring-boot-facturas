package com.makadown.springboot.app.models.entity;

import javax.persistence.*;

/**
 * @author Mario Serrano
 */
@Entity
@Table(name="facturas_items")
public class ItemFactura implements java.io.Serializable {
  
	private static final long serialVersionUID = 3084551343029051034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	 
    public ItemFactura() {
    } 
    
    private Integer cantidad;
    
    /* Muchos items de facturas pueden tener un producto 
     * OJO: Producto NO necesita relacion con itemFactura en su clase.
     * Es relación unidireccional.
     * */    
    @ManyToOne( fetch=FetchType.LAZY )
    @JoinColumn(name="producto_id")
    private Producto producto;

	public Integer getCantidad() {
		return cantidad;
	} 

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
		
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getId() {
		return id;
	} 
	
    public Double calcularImporte() {        
        return cantidad.doubleValue() * producto.getPrecio();
    }
}