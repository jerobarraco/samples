package tpmet;

/* Esta clase representa a cada una de las personas autorizadas.
En relacion con un cliente */
import javax.persistence.*;

@Entity
@Table(name="AUTORIZADOS")
public class Autorizado extends Persona {
	
	@ManyToOne(targetEntity=Cliente.class)
	@JoinColumn(name="CLI_ID", nullable=false)
	Cliente autorizador;
	
	String parentesco;

	public Cliente getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(Cliente autorizador) {
		this.autorizador = autorizador;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
}