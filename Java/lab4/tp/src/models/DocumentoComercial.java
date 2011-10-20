package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
//TODO revisar doc de herencia
//TODO poner numero como unique
@Entity
@Table(name="DOCUMENTOS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class DocumentoComercial implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_DOC")
	@SequenceGenerator(name="GEN_DOC", sequenceName="DOC_SEQ")
	protected Long id;
	protected Long numero=0L;
	//es abstracta no tendría sentido usar un getById 
	//mas porque jpa puede pensar que debe traer un objeto del tipo DocumentoComercial aun siendo una clase heredada
		
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
