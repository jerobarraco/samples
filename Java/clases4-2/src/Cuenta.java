import java.util.Date;
import java.util.Vector;

public class Cuenta {
	private int nro;
	private Vector<Transaccion> Transacciones;
	private double saldo;
	Cuenta (int pNro){
		nro = pNro;
		Transacciones = new Vector<Transaccion>();
		saldo = 0;
	}
	void addTrans(char Type, double monto, Date fecha){
		Transaccion tmp;
		switch (Type){
			case	'e': tmp = new Extraccion(monto, fecha); break;
			default: tmp = new Deposito(monto, fecha); break;
		}
		Transacciones.add(tmp);
	}
	double Saldo(){//uso funcion porque se requiere usar instanceof
		double m ;
		saldo = 0;
		for(Transaccion t: Transacciones){
			m = t.Monto();
			if (t instanceof Extraccion ){
				m = -m;
			}
			saldo += m;
		}
		return saldo;
	}
}
