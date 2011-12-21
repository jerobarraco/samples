package tpmet;
import java.io.Serializable;
import javax.persistence.*;
@Entity
@Table(name="ESTADOCOPIAS")
public class EstadoCopia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_ESTADOCOPIAS")
	@SequenceGenerator(name="GEN_ESTADOCOPIAS", sequenceName="SEQ_ESTADOCOPIAS")
	private Long id;
	String nombre;

	@Override
	public String toString() {
		return  nombre ;
	}

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