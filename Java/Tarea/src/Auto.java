
/*
		 * Generar una clase Auto que extienda de Vehiculo,
		 */

public class Auto extends Vehiculo {
	private void init(){
		//inicializacion
		
		ruedas = 4;
		seatbelts = 4;
		pasajeros = 5;
		color = 0; //negro :D
		cm3 = 150;
		marca = "fiat";
		kms =0;
		modelo = 1998;
	}
	public Auto(long pkms){
		init();
		kms = pkms;
	}
	public Auto() {
		//Si defino un constructor con parámetros java quiere que ponga uno default D:!
		
		super();
		init();
	}
	public String toString(){
		return marca +  " modelo "+ modelo + ", recorrido: " +kms;
	}
	public Long getKms(){
		return kms;
	}
	//imaginen el resto de los get/sets

}
