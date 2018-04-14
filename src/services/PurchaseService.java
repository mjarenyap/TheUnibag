package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import beans.Purchase;
/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */

public class PurchaseService {
	public static void addOrder(Purchase purchase)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			em.persist(purchase);
			trans.commit();
		}catch(Exception e){
			if(trans!=null)
				trans.rollback();
			
			e.printStackTrace();
		}
		
		em.close();
	}
	
	public static Purchase getOrder(long id)
	{
		Purchase orders = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		orders = em.find(Purchase.class, id);
		trans.commit();
		
		em.close();
		
		return orders;
		
	}

	public static boolean updateOrder(long id, Purchase newinfo)
	{
		boolean success = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			//find a bag
			Purchase a = em.find(Purchase.class, id);
			
			a.setStatus(newinfo.getStatus());
			trans.commit();
			
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		return success;
		
	}
	
	public static void deleteOrder(long id)
	{	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			Purchase a = em.find(Purchase.class, id);
			em.remove(a);
			trans.commit();
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}
	}
	
	public static List<Purchase> getAllOrders()
	{
		List<Purchase> orders = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Purchase> query = em.createQuery("select purchase from purchase purchase", Purchase.class);
			orders = query.getResultList();
			
			trans.commit();
		
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			// e.printStackTrace();
		}finally{
			em.close();
		}
		
		return orders;
		
	}

}
