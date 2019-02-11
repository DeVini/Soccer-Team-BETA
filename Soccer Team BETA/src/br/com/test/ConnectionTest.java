package br.com.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnectionTest {

	
	private static EntityManagerFactory factory = null;
	
	 public static void main(String[] args) {
		  
		  EntityManager em = EntityManagerUtil.getEM();
		        System.out.println("Servidor conectado com sucesso!");
		        
		        em.getTransaction().begin();
		        if (factory == null) {
		            factory = Persistence.createEntityManagerFactory("sgvaPU");
		        }
		        em.getTransaction().commit();
		        em.close(); 
	 }
}
