

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PROVINCIAS")
public class Provincia implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_DIRECCION")
	@SequenceGenerator(name="GEN_DIRECCION", sequenceName="DIR_SEQ")
	private Long id;
	
	//@OneToMany(targetEntity=models.Localidad.class, mappedBy="provincia")
	//private Set<Localidad> localidades = new HashSet<>();;
	
	private String nombre;

	/*public Set<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(Set<Localidad> localidades) {
		this.localidades = localidades;
	}*/
	
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
	
	
}
