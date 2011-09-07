
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import models.*;

public class m {
	 public static void main(String[] argv){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
    EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
    tx.begin();
    try{
				Sujeto s = new Sujeto();
				s.setTurno(Sujeto.Dias.DOMINGO);
				em.persist(s);
				tx.commit();
				tx = em.getTransaction();
				tx.begin();
				
				Articulo a = new Articulo();
				em.persist(a);
        tx.commit();
    }catch(Exception e){
				e.printStackTrace();				
			
        tx.rollback();
				
    }
    em.close();
	}
}
