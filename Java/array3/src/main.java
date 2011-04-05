import java.util.Vector;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] a1 = {"lo que ", "eleva lo ", "humano ", "es ", "el ", "amor."};
		String[] a2;
		int[] n1 = {1,2,3,4,5,6,7,8,9};
		int[] n2;
		a2 = a1;
		n2 = n1;
		for (int i= 0; i< a2.length; i++){ //for feo
			System.out.println("a2["+i+"] = " + a2[i] );
		}
		a2 = null; //borramos referencia
		n1 = null;
		for (int i= 0; i< n2.length; i++){ //for feo
			System.out.println("n2["+i+"] = " + n2[i] );
		}
		
		Vector<Object> z1 = new Vector<Object>();
		z1.add(10);
	}

}
