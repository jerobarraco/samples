package form;
import javax.swing.*;

import model.Universidad;

import form.*;

public class m {
	public static Universidad uni = new Universidad() ;
	public static void main(String[] args) {
		Menu.show();
		return;
		
		/*
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				FMain f = new FMain();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		});*/
	}

}
/*
Estimados alumnos
el trabajo consiste en desarrollar en Java las clases y m�todos requeridos para
 armar _una coleccion de tipo TreeMap_ que incluya objetos de tipo alumnos.
las clases involucradas son:
 
Clase Persona: con atributos m�nimos  Nombre, Apellido, Nro Documento, telefono
 
Clase Alumno (hereda de persona): nro libreta,  carrera
  
Clase Carrera: nombre, cantidad de materias, cantidad de a�os, titulo final
 
se pretende que para presentarse en la mesa de evaluaci�n final, 
el alumno lleve en medios digitales (pendrive) el proyecto con las clases ya generadas.
debe incluir una clase adicional (menu), que permita la carga de todos los elementos de tipo alumno necesarios, 
los agregue a un TreeMap, hasta que no se desee ingresar mas objetos, 
y luego se muestren los datos ordenados por Apellido por pantalla.
 
nota: se debe permitir el ingreso de una cantidad no predefinida de alumnos, 
por lo que deber� contemplarse alg�n m�todo o forma para terminar el ingreso 
y proceder a la impresi�n del listado correspondiente.*/