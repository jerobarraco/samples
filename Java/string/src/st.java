
public class st {
	public static void main(String args[]){
		clase c = new clase("hugo");
		c.MostrarNombre("");
		String txt = "laboratorio 3 tema JAVA manejo de String";
		String txt1 = "laboratorio 3 tema de JAVA manejo de String";
		System.out.println("char charat(2): " + txt.charAt(2));
		System.out.println("int compareTo "+txt.compareTo("laboratorio 3 tema JAVA manejo de String"));
		System.out.println("int compareTo "+txt.compareTo("laboratorio 3 tema asdf"));
		System.out.println("int compareTo "+txt.compareTo("laboratorio 3 tema ZZ"));
		System.out.println("boolean endswith " + txt.endsWith("g"));
		System.out.println("boolean equals "+ txt.equals(txt1));
		System.out.println("int indexof "+txt.indexOf("a",3));
		System.out.println("int lastindexof "+txt.indexOf("a", 4));
		System.out.println("int lastindexof "+txt.lastIndexOf("ma"));
		System.out.println("int lastindexof "+txt.lastIndexOf("ma",29));
	}
}
