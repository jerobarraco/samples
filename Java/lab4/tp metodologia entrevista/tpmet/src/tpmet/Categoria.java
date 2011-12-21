/* La categoría a la que pertenece un producto. 
Pej: Estreno, media rotacion, baja rotacion.
A cada categoria le corresponde una bonificacion aplicada sobre el precio del producto.
pej, un estreno puede estar un 20% mas caro si se le coloca una bonificacion de 1.20
*/
package tpmet;
import java.io.Serializable;
import javax.persistence.*;

@Entity 
@Table(name="CATEGORIAS")
public class Categoria implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_CATEGORIA")
	@SequenceGenerator(name="GEN_CATEGORIA", sequenceName="SEQ_CATEGORIA")
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
	
}
