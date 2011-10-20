package models;

import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;

@Entity //necesario para preservar el id
@Table(name="ENTES")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class EnteComercial extends Persona{
	public enum SituacionIva
  {
   MONOTRIBUTO, RI, RNI, EXENTO, CFINAL
  }
	private String cuit;
	
	@Enumerated(EnumType.STRING) //default el nombre del enum
	private SituacionIva sitIva;
	
	@Lob
	private String notas;
	
	@OneToMany(mappedBy = "destino", targetEntity=DocumentoComercial.class, cascade=CascadeType.ALL)
	private Set<DocumentoComercial> docs = new HashSet<>();;

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public SituacionIva getSitIva() {
		return sitIva;
	}

	public void setSitIva(SituacionIva sitIva) {
		this.sitIva = sitIva;
	}

	public Set<DocumentoComercial> getDocs() {
		return docs;
	}

	public void setDocs(Set<DocumentoComercial> docs) {
		this.docs = docs;
	}
	 
}
