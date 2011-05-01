import java.util.Vector;

public class Banco {
	String nombre;
	Vector<Cliente> clientes;
	Banco(String pNombre){
		nombre = pNombre;
		clientes = new Vector<Cliente>();
	}
	void AddCliente(Cliente c){
		clientes.add(c);
	}
	
}
