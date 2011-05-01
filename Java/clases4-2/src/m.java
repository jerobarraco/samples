import java.util.Date;

public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banco banco = new Banco("banco");
		
		Cliente cliente = new Cliente("juan", "perez");
		
		Cuenta cuenta = new Cuenta(1);
		
		cuenta.addTrans('d', 100.5, new Date(10, 10, 2010) );
		cuenta.addTrans('e', 10.0, new Date(11, 10, 2010) );
		
		cliente.AddCuenta(cuenta);
		banco.AddCliente(cliente);
		 
		System.out.println(cuenta.Saldo());
		
		
		
	}

}
