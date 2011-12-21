/*El empleado*/
package tpmet;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="EMPLEADOS")
public class Empleado extends Persona {
	Double sueldo;
	Integer horas;

	@ManyToOne(targetEntity=Sucursal.class)
	@JoinColumn(name="id_sucursal")
	Sucursal sucursal;
	
	public static List<Cliente> getAll(){
		return manager.nativeQueryList(Cliente.class, "Select * from EMPLEADOS;");
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
}