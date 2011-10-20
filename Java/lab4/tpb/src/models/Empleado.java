package models;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EMPLEADOS")
public class Empleado extends Persona implements Serializable{
	private String cargo;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String toString() {
		return "Empleado{" + "cargo=" + cargo + ", persona="+ super.toString()+"}";
	}
	
}
