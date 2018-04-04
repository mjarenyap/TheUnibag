package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import beans.Order;
/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */

public class OrderService {
	
	public static void addOrder(Order order)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			em.persist(order);
			trans.commit();
		}catch(Exception e){
			if(trans!=null)
				trans.rollback();
			
			e.printStackTrace();
		}
		
		em.close();
	}
	
	public static Order getOrder(long id)
	{
		Order orders = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		orders = em.find(Order.class, id);
		trans.commit();
		
		em.close();
		
		return orders;
		
	}

	public static boolean updateOrder(long id, Order newinfo)
	{
		boolean success = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			//find a bag
			Order a = em.find(Order.class, id);
			
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
			Order a = em.find(Order.class, id);
			em.remove(a);
			trans.commit();
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}
	}
	
	public static List<Order> getAllOrders()
	{
		List<Order> orders = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Order> query = em.createQuery("select order from order order", Order.class);
			orders = query.getResultList();
			
			trans.commit();
		
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		return orders;
		
	}

}
