package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="Bloques")
public class Bloque implements Serializable {
	@Id
	@GeneratedValue(generator="gen_peri", strategy= GenerationType.AUTO)
	@SequenceGenerator(name="gen_peri", sequenceName="gen_peri1")
	private Long id;	//Genera id automático 1 a 1.

	@ManyToMany(
		targetEntity=Elemento.class,
		cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	@JoinTable(
		name="bloques_elementos",
		joinColumns=@JoinColumn(
			name="bloque_id"),
      inverseJoinColumns=@JoinColumn(name="elemento_id")
  )
	private Set<Elemento> elementos = new HashSet(); /* Es una relación de muchos a muchos
																									*por que  una propaganda o un tema puede pertenecer a
																									* varios bloques.*/

	@ManyToOne(targetEntity=Programa.class)
	@JoinColumn(name="id_programa")
	private Programa programa; //Guarda la referencia de un programa en el bloque.
														 //O sea, la necesidad es que el bloque sepa a que programa pertenece. 

	public static List<Bloque> getAll(){
		manager m = new manager();
		return m.QueryList(Bloque.class, "SELECT b FROM Bloque AS b");//Con este JPQL devuelve todas las tuplas de bloques.
		//Es estático para ser usado genéricamente por todos los html para que devuelva la lista de bloques.
	}
	
	public Date getDuracion(){
		//Es tan asqueroso Java que no tiene un método fácil para sumar dos fechas.
		//Lo que se hace es recuperar el número de fecha como Long y sumar los dos long
		//Como el resultado que se necesita es de Hora, minutos y segundos, el resto de la fecha se descarta.
		long acumulador = 0L;
		for(Elemento e : elementos){
			acumulador += e.getDuracion().getTime();
		}
		return new Date(acumulador);
	}
	
	public void setId(Long id){
			this.id = id;         
	}
	public Long getId(){
			return id;
	}

	
	public void setElementos(Elemento ele){
			elementos.add(ele);//Agrega un tema o una propaganda al bloque.
	}
	public Set<Elemento> getListaElemento(){
			return elementos;
	}

	public Set<Elemento> getElementos() {
		return elementos;
	}

	public void setElementos(Set<Elemento> elementos) {
		this.elementos = elementos;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}	
}
