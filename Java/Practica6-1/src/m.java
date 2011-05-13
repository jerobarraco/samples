
public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ejercicio1();
	}
	public static String ReadLine(){
		String line = "";
		try{
			char c ;
			
			while ((c=(char)System.in.read() )!='\n'){
				line += c;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return line.trim();
	}
	public static Number ReadNumber(){
		String tmp = ReadLine();
		tmp = tmp.replace( ',', '.' );
		if (tmp.contains(".")){
			return new Double(tmp);
		}else{
			return new Integer(tmp);
		}
		/*boolean t = tmp.contains(".");
		return t ? new Double (tmp) : new Integer(tmp);*/ 
	}
	public static void ejercicio1(){
		Number n1, n2;
		try{
			n1 = ReadNumber();
			n2 = ReadNumber();
			
			if ( ( n1 instanceof Double) && (n2 instanceof Double) ){
				Double d1 = (Double)n1;
				Double d2 = (Double)n2;
				System.out.println("El maximo es "+ Math.max(d1, d2));
				System.out.println(d1+" "+d2);
			} else{
				if ((n1 instanceof Double) || (n2 instanceof Double)){
					Double d1 = n1.doubleValue();
					Double d2 = n2.doubleValue();
					System.out.println(Double.toHexString(d1) + " " +Double.toHexString(d2));
				} else{
					Integer i1 = (Integer) n1;
					Integer i2 = (Integer) n2;
					System.out.println(Integer.toBinaryString(i1)+" - "+Integer.toBinaryString(i2));
					System.out.println(Integer.toHexString(i1)+" - "+Integer.toHexString(i2));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
