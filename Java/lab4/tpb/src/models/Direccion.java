package models;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion implements Serializable{
	private String calle;
	private String depto;
	private Integer numero;


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
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String toString() {
		return "Direccion{" + "calle=" + calle + ", depto=" + depto + ", numero=" + numero + '}';
	}
	
}
