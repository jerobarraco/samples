package tpmet;
/*
Esta clase implementa la relacion de alquiler entre cliente y cada copia.
Dado que la relacion requiere conservar datos extras (inicio, fin, etc) 
respecto de la relacion en si, se debe realizar de esta manera (segun lo investigado)
*/
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

@Entity 
@Table (name="ALQUILERES")
public class Alquiler implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_ALQUILER")
	@SequenceGenerator(name="GEN_ALQUILER", sequenceName="SEQ_ALQUILER")
	Long id;
	
	Date inicio;
	Date fin;
	Date devolucion;
	String observaciones;
	
	@ManyToOne(targetEntity=Cliente.class)
	@JoinColumn(name="id_cliente")
	Cliente cliente;
	
	@ManyToOne(targetEntity=Copia.class)
	@JoinColumn(name="id_copia")
	Copia copia;

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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Date devolucion) {
		this.devolucion = devolucion;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
