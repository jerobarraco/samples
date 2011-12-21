/*
clase que modela al cliente. 
Contiene todos los datos de una persona.
Ademas el código usado en el sistema anterior (legacy), fecha de alta, notas, estado, 
si presento la fotocopia del dni y servicio. Y permite acceder a los datos sobre alquileres y autorizados
*/
package tpmet;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="CLIENTES")
public class Cliente extends Persona {

	Long codigo;
	Integer prepagos=0;
	
	//@Temporal (TemporalType.DATE) //solo para java.util.Date pero no uso esa
	Date alta;
	
	@Lob
	String notas;
	
	@org.hibernate.annotations.Type(type="boolean")
	boolean fcDni;
	
	@org.hibernate.annotations.Type(type="boolean")
	boolean fcServicio;
	
	@OneToMany(targetEntity=Autorizado.class, mappedBy="autorizador")
	Set<Autorizado> autorizados = new HashSet();
	
	@OneToMany(targetEntity=Alquiler.class, mappedBy="cliente")
	Set<Alquiler> alquileres = new HashSet();
	
	@ManyToOne(targetEntity=EstadoCliente.class)
	@JoinColumn(name="id_estado")
	EstadoCliente estado;
	
	@ManyToOne(targetEntity=Sucursal.class)
	@JoinColumn(name="id_sucursal")
	Sucursal sucursal;

	
	public Date getAlta() {
		return alta;
	}

	public void setAlta(Date alta) {
		this.alta = alta;
	}

	public Set<Autorizado> getAutorizados() {
		return autorizados;
	}

	public void setAutorizados(Set<Autorizado> autorizados) {
		this.autorizados = autorizados;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public boolean isFcDni() {
		return fcDni;
	}

	public void setFcDni(boolean fcDni) {
		this.fcDni = fcDni;
	}

	public boolean isFcServicio() {
		return fcServicio;
	}

	public void setFcServicio(boolean fcServicio) {
		this.fcServicio = fcServicio;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Integer getPrepagos() {
		return prepagos;
	}

	public void setPrepagos(Integer prepagos) {
		this.prepagos = prepagos;
	}

	public Set<Alquiler> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Set<Alquiler> alquileres) {
		this.alquileres = alquileres;
	}

	public EstadoCliente getEstado() {
		return estado;
	}

	public void setEstado(EstadoCliente estado) {
		this.estado = estado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public static List<Cliente> getAll(){
		return manager.nativeQueryList(Cliente.class, "Select * from clientes;");
	}

	@Override
	public String toString() {
		return String.format("%s, %s (Codigo: %s) (Prepagos: %s) [%s]", apellido, nombre, codigo, prepagos, estado);
	}
	
}