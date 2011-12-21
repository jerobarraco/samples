/*
Genero al que pertenece un producto.
PEj accion, suspenso, romance, thriller, etc
*/
package tpmet;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="GENEROS")
public class Genero implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_GENERO")
	@SequenceGenerator(name="GEN_GENERO", sequenceName="SEQ_GENERO")
	private Long id;
	String nombre;

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
