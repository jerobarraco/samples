public class CPU{
	public String codigo;
	public String modelo;
	public String fabricante;
	public int nucleos;
	public double precio;
	public float velocidad;
	public int stock;
	public CPU(){};
	public CPU(String linea){
		String[] attrs = linea.split(",");
		this.codigo = attrs[0];
		this.modelo =  attrs[1];
		this.fabricante =  attrs[2];
		this.nucleos = Integer.parseInt( attrs[3]);
		this.velocidad = Float.parseFloat( attrs[4]);
		this.precio = Double.parseDouble( attrs[5]);
		this.stock = Integer.parseInt( attrs[6]);
	}
	public String toCSV(){
		String tmp; 
		tmp = codigo + "," +modelo + "," +  fabricante +"," + String.valueOf(nucleos) + "," + String.valueOf(velocidad); 
		tmp += "," + String.valueOf(precio) + "," + String.valueOf (stock);
		return tmp;
	}
	public String toString(){
		return toCSV();
	}
}