package tpmet;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="PROVEEDOR")
public class Proveedor implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_PROVEEDOR")
	@SequenceGenerator(name="GEN_PROVEEDOR", sequenceName="SEQ_PROVEEDOR")
	private Long id;
	String nombre;
	String telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}