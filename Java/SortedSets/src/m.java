import java.util.*;


public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numeros[]={34,22,10,60,30,22};
		Set<Integer> set = new HashSet<Integer>();
		
		for (int i=0; i<5; i++){
			set.add(numeros[i]);
		}
		System.out.println(set);
		
		
		TreeSet sset = new TreeSet<Integer>(set);
		System.out.println("Este está ordenado "+sset);
		System.out.println("El primero es "+ (Integer)sset.first());
		System.out.println("El ultimo es "+(Integer)sset.last());
	}

}
