/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;
import model.*;
/**
 *
 * @author Administrador
 */
public class JavaApplication7 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Direccion d = new Direccion();
		d.setId(1L);
		manager.persist(d);
		Direccion d1 = manager.getById(Direccion.class, 1L);
		System.out.println(d1);
	}
}
