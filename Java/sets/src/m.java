import java.util.*;
public class m {

	/**
	 * @param args
	 */
	public static void mostrar(Set s){
		Object o=new String();
		Iterator i = s.iterator();
		while(i.hasNext()){
			o = i.next();
			System.out.println(o.toString());
		}/*
		for (Object o : s){
			System.out.println("");
		}*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set set = new HashSet();
		set.add("asldk");
		set.add("hola");
		set.add(1);
		mostrar(set);
		System.out.println("ad");
	}

}
