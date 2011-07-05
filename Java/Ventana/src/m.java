import java.awt.BorderLayout;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * Ej1 un programa que acepte numeros y al final muestre la suma, 
 * pede ingresarse hasta 10 numeros, si se ingresa un valor que no es numerico
 * dar la suma de los ingresados hasta ahi
 * si se excede , capturar excepcion que lanza el array
 * 
 * 
 * ej2 crear un array de 5 elementos donde vamos a agregar 5 objs del tipo persona, 
 */
public class m extends JFrame{
	private JLabel l;
	
	public m(){
		GregorianCalendar a = new GregorianCalendar();
        this.setSize(400,500);
        this.setTitle("Primer Aplicacion Swing");
        l = new JLabel("Something to look at",
        		new ImageIcon("images/beach.gif"), JLabel.CENTER);
        l.setVerticalTextPosition(JLabel.TOP);
        l.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(l, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
	}
	public static void Ej2() throws ENotFound, Exception{
		Persona[] ps = new Persona[4];
		//cargax
		for (int i = 0; i<3; i++){
			ps[i] = new Alumno (Integer.toString(i), Integer.toString(i), i, i, i, i%2==0);
		}
		//
		String n = JOptionPane.showInputDialog("Ingrese el nombre a buscar");
		
		for (int i = 0; i<3; i++){
			Persona p  = ps[i];
			if (p.getNombre().compareTo(n)==0){
				JOptionPane.showMessageDialog(null, "El alumno es el item "+i);
				return;
			}
		}
		throw new ENotFound();
	}
	public static void Ej1(){

		int i = 0;
		boolean dentro = true;
		Integer[] nums= new Integer[10];
		int suma = 0;
		
		try{
			while (dentro){
				String txt = JOptionPane.showInputDialog("Ingrese un numero");
				nums[i] = Integer.parseInt(txt);
				suma += nums[i++];
			}
		}catch (Exception e){
			if (e instanceof java.lang.NumberFormatException){
				System.out.println("Debe ingresar un número");
			}
			if (e instanceof java.lang.ArrayIndexOutOfBoundsException){
				//todo bien :D pase
			}
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "La suma es "+ suma);
	}
	public static void main(String args[]){
		new m();		
		try{
			Ej2();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
