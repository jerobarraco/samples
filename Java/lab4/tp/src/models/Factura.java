/*
 * id:integer
 * numero:integer
 * ptoVenta:integer
 * cliente:String
 * dirCliente:String
 * telCliente:String
 * cuitCliente:String
 * fecha:Date
 * enviar:Boolean
 * tipo:Char
 * notas:String
 * 
 */
package models;
import java.io.Serializable;
import java.sql.Date; //el de util anda mal.
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;

@Entity
@Table(name="FACTURAS")
public class Factura extends DocumentoComercial implements Serializable{		
	private Integer ptoVenta=0;
	
	//@Temporal (TemporalType.DATE) //no parece ser necesario excepto para guardar solo la fecha
	private Date fecha;
	
	@org.hibernate.annotations.Type(type="boolean")
	private boolean enviar=false;
	
	@Lob
	private String notas="";
	
	@ManyToOne
	private TipoFactura tipo;//can be null (?)//para hacerlo bien deberia inicializarlo en el primer tipo que encuentre
	
	@Embedded
	private Cliente cliente;
	
	// uidirectional One-to-Many usando fkey
	//mappedBy el atributo en la otra clase
  @OneToMany(mappedBy="factura", targetEntity=Item.class, cascade=CascadeType.ALL)
  @JoinColumn(name="DOC_ID")
  private Set<Item> items = new HashSet<>();
	//si uso <Item> dice que es redundante...
	
	public void remove(){ manager.remove(this); }
	
	public static Factura getById(Long id){
		return manager.getById(Factura.class, id);
	}
	public static List<Factura> getAll(){
		String sql = "SELECT * FROM FACTURAS;";
		return manager.nativeQueryList(Factura.class, sql);
	}
	public static List<Factura> getByNumero(Long num){
		String sql = "SELECT * FROM FACTURAS WHERE numero = "+ num+ ";";
		//OOooooooooooooooso pensaron que era from facturas?
		Query q = manager.nativeQuery(Factura.class, sql);
		return q.getResultList();
	}//mismo metodo para los demás, agregar segun necesitados
	
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/* otra forma:
	 * public class Employee {
  @Id
  @Column(name="EMP_ID")
  private long id;
  ...
  @ElementCollection
  @CollectionTable(
        name="PHONE",
        joinColumns=@JoinColumn(name="OWNER_ID")
  )
  @Column(name="PHONE_NUMBER")
  private List<String> phones;
  ...
}
	 */
	/* bidireccional
	 * @OneToMany(targetEntity=models.Item.class, mappedBy="FACTURAS")
	private Set<Item> items;

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}*/
	
	/*@ManyToOne//todo ver direccionalidad de la relacion
	 * //esta forma crea la tabla mas "sana" donde una factura tiene un solo cliente
	 * 
	private Cliente cliente;
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}*/
	
	/*public void save(){
		connection.save(this);
	}*/

	public boolean isEnviar() {
		return enviar;
	}

	public void setEnviar(boolean enviar) {
		this.enviar = enviar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Integer getPtoVenta() {
		return ptoVenta;
	}

	public void setPtoVenta(Integer ptoVenta) {
		this.ptoVenta = ptoVenta;
	}

	public TipoFactura getTipo() {
		return tipo;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return "Factura{" + "numero="+ numero+ "ptoVenta=" + ptoVenta + ", fecha=" + fecha + ", enviar=" + enviar + ", notas=" + notas + ", tipo=" + tipo + ", items=" + items + '}';
	}
	
}
