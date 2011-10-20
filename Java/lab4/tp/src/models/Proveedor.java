package models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Embedded;

@Entity
@Table(name="PROVEEDORES")
public class Proveedor extends Persona implements Serializable{
	private String rubro;
	
	@Embedded
	//TDO override de fieldnames...
	private Direccion dirTrabajo;

	
	//----------- GS

	public Direccion getDirTrabajo() {
		return dirTrabajo;
	}

	public void setDirTrabajo(Direccion dirTrabajo) {
		this.dirTrabajo = dirTrabajo;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	
}
