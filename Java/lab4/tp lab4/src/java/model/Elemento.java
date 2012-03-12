package model;

import form.Item;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/*import java.util.List;*/

@Entity 
@Table(name="Elementos")
@Inheritance(strategy= InheritanceType.JOINED) 
//usamos joined porque a pesar que es el más lento, es el más normalizado y podemos usar polimorfismo (ya que el id se propaga en las demás tablas)
//importante usar JPQL para hacer los queries
public class Elemento implements Serializable {
	@Id
	@GeneratedValue(generator="gen_elem",strategy= GenerationType.AUTO)
	@SequenceGenerator(name="gen_elem", sequenceName="gen_elem1")
	private Long id;
	
	@Temporal(TemporalType.TIME)
	private Date duracion = new Date(0L);
	
	@ManyToMany(
					targetEntity=Bloque.class,//Esto es la referencia del tipo de dato de que se trata.
					cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	@JoinTable(//Editamos los campos de la tabla relación para que no se haga automáticamente, nos pareció más ordenado y seguro.
		name="bloques_elementos",
		joinColumns=@JoinColumn(
			name="elemento_id"),
      inverseJoinColumns=@JoinColumn(name="bloque_id")
  )
	Set<Bloque> bloques = new HashSet();
	
	//Usamos el TemporalType.TIME para que guarde el tiempo y no solo la fecha (por si las moscas).
	public void setId(Long id){
			this.id = id;
	}
	public Long getId(){
			return id;
	}

	public void setDuracion(Date duracion){
			this.duracion = duracion;
	}
	public Date getDuracion(){
			return duracion;
	}
	
	@Override
	public String toString(){
		return Item.DateASTR(getDuracion()); //Devuelve un String del tiempo de los datos de esta clase.
		//Referencia de Item.DateASTR(Date) está explicado por qué está donde está.
	}
}
