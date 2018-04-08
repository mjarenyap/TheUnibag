package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import beans.Bag;
/**
 * @author gisellenodalo
 * version 1.0.02.25.18
 */


public class BagService {
	public static void addBag(Bag bags)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			em.persist(bags);
			trans.commit();
		}catch(Exception e){
			if(trans!=null)
				trans.rollback();
			
			e.printStackTrace();
		}
		
		em.close();
	}
	
	public static Bag getBag(long id)
	{
		Bag bags = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		bags = em.find(Bag.class, id);
		trans.commit();
		
		em.close();
		
		return bags;
	}
	
	public static void deleteBag(long id)
	{	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			Bag a = em.find(Bag.class, id);
			em.remove(a);
			trans.commit();
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}
	}
	
	public static boolean updateBag(long id, Bag newinfo)
	{
		boolean success = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			//find a bag
			Bag a = em.find(Bag.class, id);
			
			a.setName(newinfo.getName());
			a.setBrand(newinfo.getBrand());
			a.setColor(newinfo.getColor());
			a.setType(newinfo.getType());
			a.setCollection(newinfo.getCollection());
			a.setDescription(newinfo.getDescription());
			a.setPrice(newinfo.getPrice());
			a.setRating(newinfo.getRating());
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
	
	public static List<Bag> getAllBags()
	{
		List<Bag> bags = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Bag> query = em.createQuery("select bag from bag bag", Bag.class);
			bags = query.getResultList();
			
			trans.commit();
		
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		return bags;
		
	}


	public static List<Bag> getAllBags(int sortMode){
		List<Bag> bags = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();

		String[] modes = {"bag.name ASC", "bag.name DESC", "bag.price ASC", "bag.price DESC", "bag.type ASC", "bag.brand ASC"};
		
		try{
			trans.begin();
			
			TypedQuery<Bag> query = em.createQuery("select bag from bag bag order by " + modes[sortMode], Bag.class);
			bags = query.getResultList();
			
			trans.commit();
		
		}catch(Exception e){
			if(trans != null)
				trans.rollback();
			
			e.printStackTrace();
		}finally{
			em.close();
		}
		
		return bags;
	}
}
