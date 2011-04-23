
public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer n1, n2;
		n1 = new Integer(1);
		n2 = new Integer(1);
		System.out.println(n1==n2);
		System.out.println(n1!=n2);
		System.out.println(n1|n2);
		
		Byte b = new Byte( (byte)2 );
		Short s = new Short ((short) 2);
		Integer i = 10;
		Long l = 10L; 
		Float f = 10f;
		
		System.out.println(b.MAX_VALUE + " " + b.MIN_VALUE);
		System.out.println(s.MAX_VALUE + " " + s.MIN_VALUE);
		System.out.println(i.MAX_VALUE + " " + i.MIN_VALUE);
		System.out.println(l.MAX_VALUE + " " + l.MIN_VALUE);
		System.out.println(f.MAX_VALUE + " " + f.MIN_VALUE);
	}

}
