
public class Vehiculo implements Comparable<Vehiculo>{
	public int cantPasajeros;
	protected String color;
	protected double kmRecorridos;
	
	@Override
	public int compareTo(Vehiculo arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString(){
		return this.getCantPasajeros() + " " + this.getColor() + " " +
		this.getKmRecorridos();
	}
	public void vehiculocomparator(int cantPasajeros, String color, double kmRecorridos){
		this.cantPasajeros = cantPasajeros;
		this.color = color;
		this.kmRecorridos = kmRecorridos;
	}
	public int getCantPAsajeros(){
		return cantPasajeros;
	}
	public void setCantPasajeros(int cantPasajeros){
		this.cantPasajeros = cantPasajeros;
	}
	public String getColor(){
		return color;
	}
	public double getKmRecorridos(){
		return kmRecorridos;
	}
	public Integer getCantPasajeros(){
		return this.cantPasajeros;
	} 
}
