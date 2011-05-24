import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class read {
	//public static void print(String) = System.out.println;
	public static void main(String[] Args){
		BufferedReader bi = new BufferedReader( new InputStreamReader(System.in));
		ArrayList<Object> vec = new ArrayList<Object>();
		int s = 10;
		for (int i=0; i<s; i++){
			System.out.print("Gimme number: ");
			try{
				int n = Integer.parseInt(bi.readLine());
				vec.add(n);
			}
			catch (Exception err){
				System.out.println("ERROR");
			}
		}
		
		System.out.println("ingresados " + vec.size());
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		double prom = 0.0d;
		
		for (int i=0; i<s ; i++){
			int c = (Integer) vec.get(i);
			if (c<min){
				min = c;
			}
			if (c>max){
				max = c;
			}
			prom += c;
		}
		prom /= s;
		System.out.println("El mayor es "+max);
		System.out.println("EL menor es "+min);
		System.out.println("El promedio es "+prom);
		System.out.println("Variables mayores al promedio:");
		for (int i = 0; i<s;i++){
			if ((Integer)vec.get(i) >prom ){
				System.out.println(vec.get(i).toString());
			}
		}
		
	}
}
