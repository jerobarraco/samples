package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

//TODO revisar jerarquia, si DOCCOmercial implementa serializable capaz no es necesario
@Entity
@Table(name="REMITOS") //una tabla con remos chiquitos
public class Remito extends DocumentoComercial implements Serializable{
	
	public static Remito getById(Long id){
		return manager.getById(Remito.class, id);
	}
}
