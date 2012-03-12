package model;

import form.Item;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Temas")
public class Tema extends Elemento {
	//Referencia: Clase Propaganda línea 11.
	@Column(length=50)
	private String titulo;
	
	@Column(length=50)
	private String autor;
  
	public static List<Tema> getAll(){
		manager m = new manager();
		return m.QueryList(Tema.class, "SELECT t FROM Tema AS t");
	}//Devuelve todas las propagandas dando vueltas en la base de datos.
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public String getTitulo(){
		return titulo;
	}

	public void setAutor(String autor){
		this.autor = autor;
	}

	public String getAutor(){
		return autor;
	}
	
	@Override
	public String toString(){
		//Devuelve el String con todos los datos juntos.
		return autor + " - " + titulo + " (" + Item.DateASTR(getDuracion()) +")";
	}
}
