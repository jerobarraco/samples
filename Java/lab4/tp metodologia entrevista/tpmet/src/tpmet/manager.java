package tpmet;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
//TODO usar clase Query pelada para hacer los queries y usar JQL
public class manager {
	//private static emf = Persistence.createEntityManagerFactory("pu1");
	private static EntityManager em = Persistence.createEntityManagerFactory("pu1").createEntityManager(); //todo poner el entitymanager como estatico y devolver ems
	public static void persist(Object o){
		em.persist(o);
	}
	public static void commit(){
		EntityTransaction tx = em.getTransaction();
    tx.begin();
		try{ 
			tx.commit();
		}catch(Exception e){
				e.printStackTrace();
        tx.rollback();
    }
	}
	//hacemos publica una forma de obtener un coso por el id
	//basicamente es un foward del find... claro podriamos poner todos los gets acá, pero no es la onda de POO
	public static <T> T getById(Class<T> c, Long id){
		return em.find(c, id);
	}
	/*
	public static <T> T getAll(){
		 TypedQuery<T> q2 = em.createQuery("SELECT c FROM Country c", T.class);

public List<POJO> findByCriterion(Class c, Collection<Criterion> criterion, Collection<Order> orders){ 
 Criteria crit = createCriteria(c); 
 if (criterion!=null){ 
 for (Criterion cr : criterion){ 
 crit.add(cr); 
 } 
 } 
 if (orders!=null){ 
 for (Order order : orders){ 
 crit.addOrder(order); 
 } 
 } 
 return crit.list(); 
 }
	}*/
	public static <T> Query nativeQuery( Class<T> c , String query){
		return em.createNativeQuery(query, c);
	}
	public static <T> List<T> nativeQueryList(Class<T> c, String query){
		return em.createNativeQuery(query, c).getResultList();
	}
	public static void remove(Object o){
		em.remove(o);
	}
	
	public static boolean contains(Object o){
		return em.contains(o);
	}
	
	public static void save(Object o){
		EntityTransaction tx = em.getTransaction();
    tx.begin();
		try{ 
			em.persist(o);//TODO investigar si esto es bueno ponerlo al iniciar la instancia de los objetos
			//aparentemente es posible, habria que checkear si el objeto está "managed"
			tx.commit();
		}catch(Exception e){
				e.printStackTrace();
        tx.rollback();
    }
		//em.close() //genial, no tenemos destructores en java, como si alguien necesitara hacer cleanups
	}
	
	
}


/*
 * Nota, podría haber creado clases managers, 
 * implementando así una arquitectura en mas capas,
 * pero por la simplicidad del orm prefiero poner la lógica básica en los objetos a persistir
 * para que su uso sea mas natural
 * 
 * de hecho la clase misma de java persistence se llama EntityManager.. 
 */