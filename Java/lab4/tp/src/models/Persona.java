package models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Lob;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;


@Entity
@Table(name="PERSONAS")
@Inheritance(strategy=InheritanceType.JOINED)
public class Persona implements Serializable{

	@Id
	@GeneratedValue(generator="GEN_PERS", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="GEN_PERS", sequenceName="PERSONAS_SEQ")
	private Long id;

	private String nombre, apellido, telefonos;
	
	private Date nacimiento;
	
	@Lob
	private String observaciones;
	
	@Embedded
	private Direccion dirPersonal;

	@OneToOne(targetEntity=DatosPersona.class, mappedBy="persona")
	private DatosPersona datos;
	
	//------------- GS
	
	public Direccion getDirPersonal() {
		return dirPersonal;
	}

	public void setDirPersonal(Direccion dirPersonal) {
		this.dirPersonal = dirPersonal;
	}

	public DatosPersona getDatos() {
		return datos;
	}

	public void setDatos(DatosPersona datos) {
		this.datos = datos;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}

