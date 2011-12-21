/*
Clase base para todas las entidades que representan personas.
Contiene sus datos básicos.
*/
package tpmet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Persona implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_PERSONA")
	@SequenceGenerator(name="GEN_PERSONA", sequenceName="SEQ_PERSONA")
	private Long id;
	
	
	String nombre;
	Long dni;
	String apellido;
	String telefono;
	String direccion;
	
	@Lob
	char[] foto;
	
	String celular;
	Integer piso;
	String mail;
	String dpto;
	String usuario;
	String password;

	public void cargarFoto(String archivo){
		BufferedReader input;
		File ar = new File(archivo);
		Long l = ar.length();
		foto = new char[l.intValue()];
		try {
			input = new BufferedReader(new FileReader(archivo));
			try {
				while (input.ready()){
					input.read(foto);
				}
			} catch (IOException ex) {
				Logger.getLogger(Tpmet.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Tpmet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public char[] getFoto() {
		return foto;
	}

	public void setFoto(char[] foto) {
		this.foto = foto;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
