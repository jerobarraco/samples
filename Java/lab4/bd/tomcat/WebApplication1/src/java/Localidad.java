


import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="LOCALIDADES")
public class Localidad implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_LOCALIDAD")
	@SequenceGenerator(name="GEN_LOCALIDAD", sequenceName="LOCALIDAD_SEQ")
	private Long id;

	private String nombre;
	
	private String caracts;
	
	/*@ManyToOne(targetEntity=Provincia.class)
	@JoinColumn(name="PROV_ID", nullable=false)
	private Provincia provincia;*/

	public String getCaracts() {
		return caracts;
	}

	public void setCaracts(String caracts) {
		this.caracts = caracts;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String toJSON(){
		return String.format("{fid:%s, nombre:\"%s\"}" , getId(), getNombre() );
	}
}
