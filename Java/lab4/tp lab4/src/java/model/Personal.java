package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

@Entity
@Table(name="Personales")
public class Personal implements Serializable {
	@Id
	@GeneratedValue(generator="gen_pers", strategy= GenerationType.AUTO)
	@SequenceGenerator(name="gen_pers",sequenceName="gen_pers1")
	private Long id;

	//@ManyToMany(mappedBy="locutores", targetEntity=Programa.class)
	@ManyToMany(//es importante no poner el mappedby sino el hibernate se confunde
					targetEntity=Programa.class,//Esto es la referencia del tipo de dato de que se trata.
					cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	@JoinTable(//Editamos los campos de la tabla relación para que no se haga automáticamente, nos pareció más ordenado y seguro.
		name="Programas_personales",
		joinColumns=@JoinColumn(
			name="personal_id"),
      inverseJoinColumns=@JoinColumn(name="programa_id")
  )
	private Set<Programa> locutorDe = new HashSet();
	//La relación de muchos a muchos se debe a que un locutor puede serlo de varios programas y
	// al mismo tiempo un programa tiene muchos locutores.
	
	@OneToMany(targetEntity=Programa.class,
		cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Set<Programa> directorDe = new HashSet();
	//El CascadeType indica lo que hace el tipo de acción que quieras realizar.
	//Si hacemos el CascadeType.ALL,  la acción también va a ser de delete,
	//en cambio con estos tres tipos de relación acumulamos las necesarias para que
	//persista, refresque y actualice la tabla.
	//Entendimos que por programa va a haber un solo director.
	private String nombre;
	private String apellido;
	private String dni;
	
	@Lob
	byte[] foto;
	
	public static List<Personal> getAll(){
		manager m = new manager();
		return m.QueryList(Personal.class, "SELECT p FROM Personal AS p");
	}//Devuelve todas las tuplas de personal, sin importar si son directores o locutores.
	//No hacemos ninguna variable de diferencia entre locutor y director por que entendimos que puede ser las dos cosas al mismo tiempo.
	@Override
	public String toString(){
		//Convierte a String todos los datos de la clase.
		return nombre + " " + apellido + " (" + dni +")";
	}
	public void setId(Long id){
			this.id = id;         
	}
	public Long getId(){
			return id;
	}        
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setApellido(String apellido){
		this.apellido = apellido;
	}
	public String getApellido(){
		return apellido;
	}

	public Set<Programa> getDirectorDe() {
		return directorDe;
	}
	public void setDirectorDe(Set<Programa> directorDe) {
		this.directorDe = directorDe;
	}
	public Set<Programa> getLocutorDe() {
		return locutorDe;
	}
	public void setLocutorDe(Set<Programa> locutorDe) {
		this.locutorDe = locutorDe;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}
