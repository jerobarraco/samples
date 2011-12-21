/* Estados posibles para los clientes y la bonificacion aplicada al precio
pej: 
	Frecuente 0.9
	Normal 1.0
	Moroso 1.1
*/
package tpmet;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="ESTADOCLIENTES")
public class EstadoCliente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_ESTADOCLIENTE")
	@SequenceGenerator(name="GEN_ESTADOCLIENTE", sequenceName="SEQ_ESTADOCLIENTE")
	private Long id;
	
	String nombre;
	Double bonificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Double bonificacion) {
		this.bonificacion = bonificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre ;
	}
	
}
