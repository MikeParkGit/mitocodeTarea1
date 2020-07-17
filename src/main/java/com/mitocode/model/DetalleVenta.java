package com.mitocode.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="detalle_venta")
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idDetalle;
	
	@Column(name="cantidad", nullable=false)
	private Integer cantidad;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_venta", nullable=false, foreignKey=@ForeignKey(name="FK_venta_detalle"))
	private Venta venta;
	
	@OneToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name="id_producto", nullable=false, foreignKey=@ForeignKey(name="FK_detalleVenta_producto"))
	private Producto producto;

	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	
	
}
