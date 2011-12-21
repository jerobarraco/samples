/*Media para cada copia y el precio.
Intencionalmente relacionado con copia y no producto, para poder tener un mismo titulo en varios formatos, cada uno con un precio diferente.
El tipo de medio en que se encuentra, tambien determina el tipo de producto (juego/pelicula)
*/
package tpmet;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="MEDIAS")
public class Media implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_MEDIA")
	@SequenceGenerator(name="GEN_MEDIA", sequenceName="SEQ_MEDIA")
	private Long id;
	String nombre;
	Double precio;

	@Override
	public String toString() {
		return nombre ;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
}
