package dos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToOne;

@Entity
@Table(name="PROFESORES")
public class Profesor implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_PROFE")
	@SequenceGenerator(name="GEN_PROFE", sequenceName="PROFE_SEQ")
	int id;
	String nombre;
	@OneToOne(cascade=CascadeType.ALL)
	private Estacionamiento estacionamiento;

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
