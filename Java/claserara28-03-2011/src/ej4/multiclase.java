/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ej4;
import static java.lang.Math.*;
/**
 *
 * @author Administrador
 */

public class multiclase {
	private int dato1;
	private int dato2;
	private char tipoFigura;

	
	public double Calcular(){
		double res= - 1d ;
		if (tipoFigura == 'c'){
			System.out.println("Circulo:");
			res = Math.PI * dato1 * dato1;
			System.out.println("\tPerímetro = " + (Math.PI * dato1*dato1));
			System.out.println("\tSuperficie = " + res);
		}else if (tipoFigura == 'r'){
			System.out.println("Cuadrado:");
			res  = dato1*dato2;
			System.out.println("\tPerímetro = " + ((dato1*2) + (dato2*2)));
			System.out.println("\tSuperficie = " + res);
		}
		return res;
	}
	public multiclase (int a){
		dato1 = a;
		dato2 = 0;
		tipoFigura = 'c';
	}
	public multiclase( int a, int b){
		dato1 = a;
		dato2 = b;
		tipoFigura = 'r';
	}
}
