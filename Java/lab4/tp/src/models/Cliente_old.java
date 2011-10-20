package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Query;

//TODO naturalizar la insercion y borrado  de facturas
//TODO metodos de listado del cientes (classmethod)
@Entity
@Table(name="CLIENTES")
public class Cliente_old implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_CLIENTE")
	@SequenceGenerator(name="GEN_CLIENTE", sequenceName="CLIENTE_SEQ")
	private Long id;
	private String nombre="";
	private String direccion="";
	private Integer cuit=0;
	
	@OneToMany(targetEntity=Factura.class)
  @JoinColumn(name="CLIENT_ID")
  private Set<Factura> facturas = new HashSet<>();

	//methods (porque los get/set no son metodos ¬_¬?)
	public boolean managed(){
		return manager.contains(this);
	}
	public void remove(){
		manager.remove(this);
	}
	
	//static
	public static Cliente getById(Long id){
		return manager.getById(Cliente.class, id);
	}
	public static List<Cliente> getAll(){
		return manager.nativeQueryList(Cliente.class, "SELECT * FROM clientes c;");//Select asterisco, el favorito del público!
	}
	public static List<Cliente> getByNameLike(String name){
		//ojo acá con el nombre del campo, hay que confiar que jpa lo nombre igual que nosotros
		String sql = "Select * FROM clientes c WHERE nombre LIKE '%" + name.toLowerCase() + "%';";
		Query q = manager.nativeQuery(Cliente.class, sql); //cambiar a lsit
		return q.getResultList();
	}
	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}
	/*@OneToMany
	@CollectionOfElements
	private List<Factura> facturas;

	 * 
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}//nada mas feo que un get y set de una lista en un objeto persistente
	*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCuit() {
		return cuit;
	}

	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", cuit=" + cuit + ", facturas=" + facturas + '}';
	}
	
}
