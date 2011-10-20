package models;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ARTICULOS")
public class Articulo implements Serializable{
	@Id
	@GeneratedValue(generator="GEN_ART", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="GEN_ART", sequenceName="ARTICULOS_SEQ")
	private Long id;
	private String nombre;
	private Double pvp;//PrecioVentaPublico no confundir con PlayerVSPlayer ;)
	private Integer existencia;
	
	@OneToMany(mappedBy="articulo", targetEntity=Item.class, cascade=CascadeType.ALL)
	private Set<Item> items = new HashSet();//no me gustan las relaciones bidireccionales
	
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

	public Double getPvp() {
		return pvp;
	}

	public void setPvp(Double pvp) {
		this.pvp = pvp;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String toString() {
		return "Articulo{" + "id=" + id + ", nombre=" + nombre + ", pvp=" + pvp + ", existencia=" + existencia + ", items=" + items.size() + '}';
	}
	
}
