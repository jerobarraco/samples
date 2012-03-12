package model;

import form.Item;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Propagandas")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Propaganda extends Elemento {
	//Propaganda es un tipo de elemento, y en la clase padre se determina la primary key. 
  @Column(length=50)
	private String empresa;

	public static List<Propaganda> getAll(){//Todo hacer esto para todos
		//Con este método devuelve la lista de todas las propagandas.
		manager m = new manager();
		return m.QueryList(Propaganda.class, "SELECT p FROM Propaganda AS p");
		//return m.nativeQueryList(Propaganda.class, "SELECT * FROM Propagandas");
	}


	public void setEmpresa(String empresa){
			this.empresa = empresa;
	}

	public String getEmpresa(){
			return empresa;
	}
 	@Override
	public String toString(){
		//toString que devuelve todos los datos en una cadena.
		return empresa  + " (" + Item.DateASTR(getDuracion()) +")";
	}     
}
