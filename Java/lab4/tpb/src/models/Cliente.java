package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
public class Cliente extends EnteComercial {
	private Integer numero;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return "Cliente{" + "numero=" + numero + ", persona="+ super.toString() + '}';
	}

	
}
