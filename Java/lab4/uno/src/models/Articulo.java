package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 *
 * @author Administrador
 */
@Entity
@Table(name="ARTICULOS")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_ARTICULO")
	@SequenceGenerator(name="GEN_ARTICULO", sequenceName="ARTICULO_SEQ")
	private Long id;

	@Basic
	private Integer idarticulo;
	
	@Basic
	@Column(length=50)
	private String codigo;
	
	@Basic
	@Column(length=200)
	private String descripcion;
	
	@Basic
	@Column(precision=18, scale=4)
	private double preciocosto;
	
	@Lob
	private String obs;
	
	@Basic 
	@org.hibernate.annotations.Type(type="integer")
	private boolean activo;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public double getPreciocosto() {
		return preciocosto;
	}

	public void setPreciocosto(double preciocosto) {
		this.preciocosto = preciocosto;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Integer getIdarticulo() {
		return idarticulo;
	}

	public void setIdarticulo(Integer idarticulo) {
		this.idarticulo = idarticulo;
	}

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Articulo)) {
			return false;
		}
		Articulo other = (Articulo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "models.Articulo[id=" + id + "]";
	}

}
