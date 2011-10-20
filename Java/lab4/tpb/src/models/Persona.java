package models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;


@Entity
@Table(name="PERSONAS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Persona implements Serializable{

	@Id
	@GeneratedValue(generator="GEN_PERS", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="GEN_PERS", sequenceName="PERSONAS_SEQ")
	protected Long id;

	private String nombre;
	
	@Embedded
	private Direccion dirPersonal;

	
	//------------- GS
	
	public Direccion getDirPersonal() {
		return dirPersonal;
	}

	public void setDirPersonal(Direccion dirPersonal) {
		this.dirPersonal = dirPersonal;
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
		return "Persona{" + "id=" + id + ", nombre=" + nombre + ", dirPersonal=" + dirPersonal + '}';
	}
	
}

