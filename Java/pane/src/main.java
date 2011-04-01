import javax.swing.JOptionPane;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("Ingrese su nombre" );
		System.out.println("el nombre es "+name);
		int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"));
		System.out.println("su edad es " + edad);
		JOptionPane.showMessageDialog(null, "Hola "+name +" con " +edad + " años", "Practica" , JOptionPane.INFORMATION_MESSAGE);
		/**
		for (int i: a){
			System.out.println(i);
		}**/
	}

}
