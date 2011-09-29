/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
public class Dos {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
    tx.begin();
    try{
				Profesor p = new Profesor();
				p.setNombre("juancito");
				
				Estacionamiento e = new Estacionamiento();
				e.setN_plaza(3);
				p.setEstacionamiento(e);
				
				em.persist(p);
				tx.commit();
				/*
				tx = em.getTransaction();
				tx.begin();
				
				Articulo a = new Articulo();
				em.persist(a);
        tx.commit();*/
    }catch(Exception e){
				e.printStackTrace();				
			
        tx.rollback();
				
    }
    em.close();
		
		// TODO code application logic here
	}
}
