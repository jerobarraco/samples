/*
Representa a una sucursal, con sus copias, clientes y empleados
*/

package tpmet;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="SUCURSALES")
public class Sucursal implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_SUCURSAL")
	@SequenceGenerator(name="GEN_SUCURSAL", sequenceName="SEQ_SUCURSAL")
	private Long id;
	
	String nombre;
	String direccion;
	String telefono;
	double caja;
	
	@OneToMany(targetEntity=Copia.class, mappedBy="sucursal")
	Set<Copia> copias = new HashSet();
	
	@OneToMany(targetEntity=Empleado.class, mappedBy="sucursal")
	Set<Empleado> empleados = new HashSet();
	
	@OneToMany(targetEntity=Cliente.class, mappedBy="sucursal")
	Set<Cliente> clientes = new HashSet();

	public static List<Sucursal> getAll(){
		return manager.nativeQueryList(Sucursal.class, "Select * from SUCURSALES;");
	}
	
	public double getCaja() {
		return caja;
	}

	public void setCaja(double caja) {
		this.caja = caja;
	}

	public Set<Copia> getCopias() {
		return copias;
	}

	public void setCopias(Set<Copia> copias) {
		this.copias = copias;
	}

	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Sucursal=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono ;
	}
	
}
