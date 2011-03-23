/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ja1;
import java.util.Scanner;
/**
 *
 * @author nande
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
			Integer n;
			if (args.length >0){
				n = new Integer(args[0]);
			} else{
				System.out.println("Ingrese un numero para calcular el factorial please");
				Scanner in = new Scanner(System.in);
				n = in.nextInt();
				in.close();
			}

			Integer f;
			Factorial fac = new Factorial();
			f = fac.Do(n);
			
			System.out.println("El resultado es:"+f);
			
      
    }

}
