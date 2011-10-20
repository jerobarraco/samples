package models;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Embeddable
public class Direccion implements Serializable{
/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_DIRECCION")
	@SequenceGenerator(name="GEN_DIRECCION", sequenceName="DIR_SEQ")
	private Long id;*/

	private String calle;
	private String depto;
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name="LOC_ID", nullable=true)
	private Localidad localidad;

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}
