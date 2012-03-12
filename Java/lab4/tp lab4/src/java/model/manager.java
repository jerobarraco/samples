package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class manager {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1"); 
	//Así como el profesor lo dejó en una clase a parte y estática que no se puede
	//instanciar, lo mismo creemos que podemos hacer si lo ponemos como un atributo estático.
	private EntityManager em;
	
	public manager(){
		em = emf.createEntityManager();//crappy manager no es threadsafe (forma rara de decir que el manager de mierda no se puede usar en un entorno multithread)
	}
	public void persist(Object o){
		//Con esto se persiste cualquier objeto.
		em.persist(o);
	}
	//hacemos publica una forma de obtener un "coso" por el id (tomemos nota de la manera muy técnica a la que Nande se refiere a los tipos de clases dentro de <T> jaja)
	//basicamente es un foward del find... claro podriamos poner todos los gets acá, pero no es la onda de POO
	public <T> T getById(Class<T> c, Long id){
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
	public <T> Query Query(Class<T> c, String query){
		//Este es un JPQL o sea el SQL especial de JPA con Hibernate que devuelve los objetos.
		//La referencia a mirar es cuando se pide las listas en todas las clases con un String dentro (aunque no llamen a este método).
		return em.createQuery(query);
	}
	public <T> Query nativeQuery( Class<T> c , String query){
		//Esto es para crear un SQL SIN ser un jpql, o sea, es un SQL que lo hace directamente a la base de datos.
		return em.createNativeQuery(query, c);
	}
	public <T> List<T> QueryList(Class<T> c, String query){
		//Devuelve una lista, a diferencia del Query normal que te devuelve el pedido.
		return this.Query(c, query).getResultList();
	}
	public <T> List<T> nativeQueryList(Class<T> c, String query){
		//Lo mismo en SQL (y no en JPQL) que el método anterior.
		return em.createNativeQuery(query, c).getResultList();
	}
	public boolean remove(Object o){
		//Remueve un objeto de la base de datos (si, si, como si fuera un objeto, en realidad remueve todos los datos que hacen referencia a ese objeto).
		EntityTransaction tx = em.getTransaction();
    tx.begin();
		try{ 
			em.remove(o);
			tx.commit();
			return true;
		}catch(Exception e){
				e.printStackTrace();
				if (tx.isActive()){//si la excepcion se realiza dentro del commit adivinen que. la transaccion queda "chota" y no podemos hacer rollback para restaurarla :D
					tx.rollback();
				}else{
					tx.begin();
				}
				return false;
    }
	}
	
	public boolean contains(Object o){
		//Devuelve true si el objeto que le pasamos está dentro del EntityManager.
		return em.contains(o);
	}
	
	public boolean save(Object o){
		//Guarda un objeto.
		//Se puede hacer con el merge, pero en algunos casos no nos convenía por que duplicaba datos.
		EntityTransaction tx = em.getTransaction();
    tx.begin();
		try{ 
			em.persist(o);
			tx.commit();
			return true;
		}catch(Exception e){
				e.printStackTrace();
				if (tx.isActive()){
					tx.rollback();
				}else{
					tx.begin();
				}
				return false;
    } 
	}

	public void flush() {
		em.flush();
	}
}
