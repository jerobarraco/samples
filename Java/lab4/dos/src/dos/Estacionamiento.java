package dos;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Estacionamiento implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_ESTA")
	@SequenceGenerator(name="GEN_ESTA", sequenceName="ESTA_SEQ")
	private int id;
	private int n_plaza;

	@OneToOne(mappedBy="estacionamiento")
	private Profesor profe;

	public Profesor getProfe() {
		return profe;
	}

	public void setProfe(Profesor profe) {
		this.profe = profe;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getN_plaza() {
		return n_plaza;
	}

	public void setN_plaza(int n_plaza) {
		this.n_plaza = n_plaza;
	}	
	
}
