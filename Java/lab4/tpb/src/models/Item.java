package models;

import java.util.List;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name="ITEMS")
public class Item implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_ITEM")
	@SequenceGenerator(name="GEN_ITEM", sequenceName="ITEM_SEQ")
	private Long id;
	
	private Integer cantidad;
	private String descripcion;
	private Double precio;
	
	@ManyToOne(targetEntity=Articulo.class)
	@JoinColumn(name="ART_ID", nullable=false)
	private Articulo articulo;
	
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public void remove(){ manager.remove(this); }
	
	public static Item getById(Long id){
		return manager.getById(Item.class, id);
	}
	
	public static List<Item> getAll(){
		return manager.nativeQueryList(Item.class, "Select * from ITEMS;");
	}
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return "Item{" + "id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + '}';
	}	
}
