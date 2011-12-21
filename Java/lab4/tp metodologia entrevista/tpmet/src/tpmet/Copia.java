/* Clase que modela cada una de las copias.
Posee un numero utilizado en el sistema legacy.
Fecha de alta, sucursal en la que se encuentra, el medio en que se encuentra, 
a que producto responde, su estado, los alquileres y reservas hechas sobre esta copia.
Y permite ademas conocer su disponibilidad y calcular el precio de la misma.
*/
package tpmet;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="COPIAS")
public class Copia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_COPIA")
	@SequenceGenerator(name="GEN_COPIA", sequenceName="SEQ_COPIA")
	private Long id;
	
	Long numero;
	Date alta;
	
	@ManyToOne(targetEntity=Sucursal.class)
	@JoinColumn(name="id_sucursal")
	Sucursal sucursal;
	
	@ManyToOne(targetEntity=Producto.class)
	@JoinColumn(name="id_producto")
	Producto producto;
	
	@ManyToOne(targetEntity=Media.class)
	@JoinColumn(name="id_media")
	Media tipo;

	@ManyToOne(targetEntity=EstadoCopia.class)
	@JoinColumn(name="id_estadocopia")
	EstadoCopia estado;

	@OneToMany(targetEntity=Alquiler.class, mappedBy="copia")
	Set<Alquiler> alquileres = new HashSet();
	
	@OneToMany(targetEntity=Reserva.class, mappedBy="copia")
	Set<Reserva> reservas = new HashSet();
	
	public boolean Alquilada() {
		for (Alquiler a : alquileres){
			if (a.devolucion != null){
				return true;
			}
		}
		return false;
	}
	
	public double CalcPrecio(Cliente cliente) {
		Double precio = tipo.getPrecio();
		precio *= producto.getCategoria().getBonificacion();
		precio *= cliente.getEstado().getBonificacion();
		return precio;
	}
	
	public boolean Reservada() {
		Timestamp ts = new Timestamp(new java.sql.Date(0).getTime()) ;
		
		for (Reserva r : reservas){
			if (r.fin != null){
				if (r.fin.compareTo(ts)<0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean Disponible() {
		return !(Alquilada() || Reservada());
	}
	
	public Double Alquilar(Cliente cliente) {
		Alquiler alq = new Alquiler();
		alq.setCliente(cliente);
		alq.setCopia(this);
		alq.inicio = new java.sql.Date(new java.util.Date().getTime() );
		alquileres.add(alq);
		manager.save(alq); 
		return CalcPrecio(cliente);
	}
	
	public Date Reservar(Cliente cliente) {
		//todo
		return new Date(0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", tipo=" + tipo + ", estado=" + estado;
	}

	public Set<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Set<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	public Date getAlta() {
		return alta;
	}

	public void setAlta(Date alta) {
		this.alta = alta;
	}

	public EstadoCopia getEstado() {
		return estado;
	}

	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Media getTipo() {
		return tipo;
	}

	public void setTipo(Media tipo) {
		this.tipo = tipo;
	}
	
}
//
