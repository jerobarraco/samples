/*
Implementa los productos.
Con los detalles basicos, las copias que posee, y un metodo para saber si posee copias disponibles
*/ 
package tpmet;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="PRODUCTOS")
public class Producto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_PRODUCTO")
	@SequenceGenerator(name="GEN_PRODUCTO", sequenceName="SEQ_PRODUCTO")
	private Long id;
	
	String titulo;
	@Lob
	String detalles;
	String pais;
	Date fecha;
	
	@OneToMany(targetEntity=Copia.class, mappedBy="producto")
	Set<Copia> copias = new HashSet();
	
	@ManyToOne(targetEntity=Genero.class)
	@JoinColumn(name="id_genero")
	Genero genero;
	
	@ManyToOne(targetEntity=Categoria.class)
	@JoinColumn(name="id_categoria")
	Categoria categoria;

	@ManyToOne(targetEntity=Proveedor.class)
	@JoinColumn(name="id_proveedor")
	Proveedor proveedor;

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	public static List<Producto> getAll(){
		return manager.nativeQueryList(Producto.class, "Select * from Productos;");
	}

	@Override
	public String toString() {
		return "Titulo=" + titulo + ", pais=" + pais + ", fecha=" + fecha ;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	public int CantDisponibles() {
		int disponibles  =0;
		for (Copia c :copias){
			if (c.Disponible()){
				disponibles ++;
			}
		}
		return disponibles;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Copia> getCopias() {
		return copias;
	}

	public void setCopias(Set<Copia> copias) {
		this.copias = copias;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
//
