package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CollectionOfElements;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name="ARTICULOS")
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum TOrigen {LOCAL, EXTRANGERO};	
	
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
	private double precioCosto;
	
	@Basic
	@org.hibernate.annotations.Type(type="big_decimal")
	@Column(precision=18, scale=4)
	private BigDecimal precioVenta;
	
	@Lob
	private String obs;
	
	@Lob
	private byte[] imagen;
	
	@Basic 
	@org.hibernate.annotations.Type(type="boolean")
	private boolean activo;

	@Temporal (TemporalType.DATE)
	private java.util.Date fechaIngreso;

	@Enumerated (EnumType.ORDINAL)
	private TOrigen origen;

	@CollectionOfElements
	private List<String> palabras_clave;
	
	public List<String> getPalabras_clave() {
		return palabras_clave;
	}

	public void setPalabras_clave(List<String> palabras_clave) {
		this.palabras_clave = palabras_clave;
	}
	
	
	
	
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public TOrigen getOrigen() {
		return origen;
	}

	public void setOrigen(TOrigen origen) {
		this.origen = origen;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
		
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

	public double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(double precioCosto) {
		this.precioCosto = precioCosto;
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
