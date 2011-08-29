package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="SUJETOS")
public class Sujeto implements Serializable {
	public enum Dias {
		LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
	}
	
	@Id
  @GeneratedValue(generator="GEN_SUJETO", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="GEN_SUJETO", sequenceName="SUJETO_SEQ")
	private int id;

	@Enumerated
	private Dias turno;

	//me dan asco los gets y sets, el tener code generator no es excusa.
	public Dias getTurno() {
		return turno;
	}

	public void setTurno(Dias turno) {
		this.turno = turno;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
