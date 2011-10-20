package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PROVEEDORES")
public class Proveedor extends EnteComercial{
	private Integer numero;
	private String razonSocial;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String toString() {
		return "Proveedor{" + "numero=" + numero + ", razonSocial=" + razonSocial + ", persona="+ super.toString() +'}';
	}
	
}
