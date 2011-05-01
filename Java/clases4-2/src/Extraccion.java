import java.util.Date;

public class Extraccion extends Transaccion {
	Date fecha;
	Extraccion (double pMonto,  Date pFecha){
		super(pMonto);
		fecha = pFecha;
	}
}
