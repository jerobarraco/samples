import java.io.*;

public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String str = "";
		String line = "";
		try{
			while ( line.toLowerCase().compareTo("fin") != 0 ){
				str += line + " ";
				line = br.readLine();
			}
		} catch (Exception e){
			System.out.println(e);
		}
		System.out.println(str.trim());
	}

}
