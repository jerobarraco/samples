package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;

@Entity
@Table(name="Programas")
public class Programa implements Serializable {
	@Id
	@GeneratedValue(generator="gen_prog", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="gen_prog", sequenceName="gen_prog1")
	private Long id;	
	
	@ManyToOne(targetEntity=Personal.class, optional=true,
		cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_director", nullable=true)
	private Personal director;
	//La relación Muchos a uno es que un director puede serlo de muchos programas.
	
	@ManyToMany(
		targetEntity=Personal.class,
		cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	@JoinTable(
		name="Programas_personales",
		joinColumns=@JoinColumn(
			name="programa_id"),
      inverseJoinColumns=@JoinColumn(name="personal_id")
  )
	private Set<Personal> locutores = new HashSet();
	//Para la referencia a esta relación está determinada en la clase Personal desde la línea 34.
	@Temporal(TemporalType.TIME)
	private Date horario;
	//La referencia del tipo de dato a mapear está determinado en clase Elemento línea 21.
	@OneToMany(targetEntity=Bloque.class, mappedBy="programa", cascade= CascadeType.ALL)
	private Set<Bloque> bloques = new HashSet();
	//Un programa está determinado por muchos bloques, por ello es una relacción de 
	//uno (el programa) a muchos (bloques).
	//importante default a 0
	private Integer dia=0;
	//Esta variable Integer determina el número de día de la semana en que se va a hacer el programa.
	//dia va a estar determinado con los valores 0 a 6 (donde 0 es Domingo).	
	public static List<Programa> getAll(){
		manager m = new manager();
		return m.QueryList(Programa.class, "SELECT p FROM Programa AS p");
	}//Función estática donde devuelve una lista de todos los programas. 
	//Para entender por qué estática, remitirse a clase Bloque línea 31
	public static List<Programa> getFiltrado(Integer d, Long idLocutor){
		if ((d == null && idLocutor == null)){
			return getAll();
		}else{
			String jpql = "SELECT p FROM Programa As p LEFT OUTER JOIN p.locutores AS loc WHERE ";
			//Demos gracias a: http://docs.oracle.com/cd/E12840_01/wls/docs103/kodo/full/html/ejb3_langref.html
			//Demos gracias a: http://www.objectdb.com/java/jpa/query/jpql/from
			//Demos gracias a: DIOS!!!!!
			//Pueden ir en paz.
			if (d!= null)
				jpql += "dia = :pdia AND ";
			
			if (idLocutor != null)
				jpql += "loc.id = :locid AND ";
			
			jpql += "1=1";
			//Te permite filtrar por locutores, por día, por los dos o por ninguno.
			manager m = new manager();	
			Query q = m.Query(Programa.class , jpql);
			if (d != null) q.setParameter("pdia", d);
			if (idLocutor != null) q.setParameter("locid", idLocutor);
			return q.getResultList();
		}
		
		//igual podriamos usar m.getById(model.Personal, idLocutor) y usar el memberOf, pero seguro es mucho mas ineficiente que esto
	}
	//GS
	public void setId(Long id){
		this.id = id;         
	}
	public Long getId(){
		return id;
	}
	public void setDirector(Personal director){
		this.director = director;
	}
	public Personal getDirector(){
		return director;
	}

	public void setHorario(Date horario){
		this.horario = horario;
	}
	public Date getHorario(){
		return horario;
	}

	public Date getDuracion() {
		//Referencia debida a este punto: Clase Bloque línea 35
		long acumulador = 0L;
		for(Bloque p : bloques){
			acumulador += p.getDuracion().getTime(); 
		}		
		return new Date(acumulador);
	}

	public Set<Bloque> getBloques() {
		return bloques;
	}

	public void setDia(Integer dia) {
		//Esto comprueba que día no se pase de la escala a la que debiera pertenecer.
		//Esta escala se debe a un vector que contiene los nombre de los días. Evitamos
		//con esto que la escala no se vaya de los límites de ese vector.
		if(dia > 6){
			dia = 6;			
		}
		else if(dia < 0){
			dia  = 0;
		}
		this.dia = dia;
	}
	
	public Integer getDia(){
		//Para evitar errores, generamos un número en día por defecto.
		if(dia == null){
			dia = 0;
		}
		return dia;
	}

	public Set<Personal> getLocutores() {
		return locutores;
	}

	public void setLocutores(Set<Personal> locutores) {
		this.locutores = locutores;
	}
}
