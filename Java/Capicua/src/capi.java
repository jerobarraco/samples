import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class capi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//escribir un programa que tome un numero y verifique si es capicua
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Object> vec = new ArrayList<Object>(6);
		int s = 6;
		try{
			
			for (int i=0; i<s;i++){
				System.out.println("gimme");
				vec.add(Integer.parseInt(in.readLine()));
			}
		}catch(Exception error){
			System.out.println(error);
		}
		boolean capi = true;
		for (int i = 0; i< s/2; i++){
			if (vec.get(i) != vec.get(s-i-1)){
				capi = false;
				break;
				
			}
		}
		if (capi){
			System.out.println("Es capicua");
		}else{
			System.out.println("No es capicua");
		}

	}

}
