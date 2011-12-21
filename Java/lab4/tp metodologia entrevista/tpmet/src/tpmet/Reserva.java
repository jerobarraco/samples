/*
Representa una reserva hecha sobre una copia para un cliente
*/
package tpmet;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name="RESERVAS")
public class Reserva implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_RESERVA")
	@SequenceGenerator(name="GEN_RESERVA", sequenceName="SEQ_RESERVA")
	private Long id;
	Date fecha;
	Timestamp fin;

	@ManyToOne(targetEntity=Copia.class)
	@JoinColumn(name="id_copia")
	Copia copia;
	
	@ManyToOne(targetEntity=Cliente.class)
	@JoinColumn(name="id_cliente")
	Cliente cliente;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Copia getCopia() {
		return copia;
	}

	public void setCopia(Copia copia) {
		this.copia = copia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFin() {
		return fin;
	}

	public void setFin(Timestamp fin) {
		this.fin = fin;
	}
}
