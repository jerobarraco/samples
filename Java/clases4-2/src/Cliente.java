import java.util.Vector;


public class Cliente {
	Vector<Cuenta> cuentas;
	String nombre, apellido;
	Cliente(String pn, String pa){
		nombre = pn;
		apellido = pa;
		cuentas = new Vector<Cuenta>();
	}
	void AddCuenta(Cuenta pc){
		cuentas.add(pc);
	}
}
