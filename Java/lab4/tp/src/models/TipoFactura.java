package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Query;

@Entity
@Table(name="TIPOS_FACTURA")
public class TipoFactura implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_FACTURA")
	@SequenceGenerator(name="GEN_FACTURA", sequenceName="FACTURA_SEQ")
	private Long id;
	
	private String nombre;

	public TipoFactura() {
		super();
		//buscar la forma de usar this en el constructor! q inutil please!!!!
	}
	public void remove() {
		manager.remove(this);
	}
	public static TipoFactura getById(Long id){
		return manager.getById(TipoFactura.class, id);
	}
	public static List<TipoFactura> getAll(){
		return manager.nativeQueryList(TipoFactura.class, "Select * From Tipos_Factura;");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return "TipoFactura{" + "id=" + id + ", nombre=" + nombre + '}';
	}
	
}
