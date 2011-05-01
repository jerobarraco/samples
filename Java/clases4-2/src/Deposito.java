import java.util.Date;

public class Deposito extends Transaccion{
	Date fecha;
	
	Deposito(double pMonto, Date pFecha){
		super(pMonto);
		fecha = pFecha;
	}
	
}
