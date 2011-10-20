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
import java.math.BigDecimal;
import java.sql.Date; //el de util anda mal.
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="DOCUMENTOS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
public class DocumentoComercial implements Serializable{	
	/*horrible combinacion de condiciones hace que esto sea mas horrible
	 * la clase DocumentoComercial no puede ser abstracta para poder hacer polimorfismo
	 * y al usar single_Table (una aberracion per se) hace que hibernate explote.
	 * porque hibernate no se da cuenta que tiene que crear la tabla padre al crear
	 * la entidad hija.... 
	 * si a eso le sumamos que el item es unidireccional y la tabla item se modifica al crear un documento,
	 */
	@Id
	@GeneratedValue(generator="GEN_DOCS", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="GEN_DOCS", sequenceName="DOCS_SEQ")
	protected Long id;
	
	@org.hibernate.annotations.Type(type="boolean")
	protected boolean enviar=false;
	
	@Lob
	protected String notas="";
	
	protected char letra;
	
	protected BigDecimal total;
	
	protected Date fecha;
	
	@ManyToOne(targetEntity=EnteComercial.class)
	@JoinColumn(name="ENTE_ID")
	protected EnteComercial destino;
	
	//uidirectional One-to-Many usando fkey
  @OneToMany(targetEntity=Item.class, cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="DOC_ID")
  protected Set<Item> items = new HashSet<>();
	//si uso <Item> dice que es redundante...
	
	public void remove(){ manager.remove(this); }
	
	public static DocumentoComercial getById(Long id){
		return manager.getById(DocumentoComercial.class, id);
	}
	
	public static List<DocumentoComercial> getAll(){
		String sql = "SELECT * FROM FACTURAS;";
		return manager.nativeQueryList(DocumentoComercial.class, sql);
	}
	
	public static List<DocumentoComercial> getByNumero(Long num){
		String sql = "SELECT * FROM FACTURAS WHERE numero = "+ num+ ";";
		//OOooooooooooooooso pensaron que era from facturas?
		Query q = manager.nativeQuery(DocumentoComercial.class, sql);
		return q.getResultList();
	}//mismo metodo para los demás, agregar segun necesitados
	
	public boolean isEnviar() {
		return enviar;
	}

	public void setEnviar(boolean enviar) {
		this.enviar = enviar;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String toString() {
		return "DocumentoComercial{enviar=" + enviar + ", notas=" + notas +  ", items=" + items + '}';
	}

	public EnteComercial getDestino() {
		return destino;
	}

	public void setDestino(EnteComercial destino) {
		this.destino = destino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
}
