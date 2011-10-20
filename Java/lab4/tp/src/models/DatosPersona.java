package models;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="DATOS_PERSONA")
public class DatosPersona implements Serializable {
	@Id
	@GeneratedValue(generator="GEN_DPERS", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="GEN_DPERS", sequenceName="DATOSPERSONAS_SEQ")
	private Long id;

	@OneToOne(targetEntity=Persona.class)
	@JoinColumn(name="PERS_ID", nullable=false)
	private Persona persona;
	
	
	@Lob
	private byte[] Imagen;	
	
	private String notas;

	
	//-------------- GS

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	public byte[] getImagen() {
		return Imagen;
	}

	public void setImagen(byte[] Imagen) {
		this.Imagen = Imagen;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
