package br.com.test;

import java.util.Calendar;
import javax.persistence.EntityManager;
import br.com.project.model.Time;
import br.com.project.model.Usuario;
import br.com.project.model.enums.Permissao;


public class TesteUsuarioTime {

	
	public static void main(String[] args) throws Exception {
		
		Time time = new Time();
		Usuario user = new Usuario();
		
		time.setNome("Galaticos F.C");
		time.setDtFundacao(Calendar.getInstance().getTime());
		time.setLocalTreino("Quadra Condominio");
		time.setMandoDeCampo("Santa Izabel");
		
		user.setUsername("viny");
		user.setPassword("123");
		user.setEmail("nyrocha2010@hotmail.com");
		user.setPermissao(Permissao.ADMIN);
		user.setTime(time);
		
		EntityManager manager = new EntityManagerUtil().getEM();
		
		manager.getTransaction().begin();
		
		try
		{
			
			manager.persist(time);
			manager.persist(user);
			manager.getTransaction().commit();
			System.out.println("Salvo com Sucesso!!");
			
		}catch(Exception e)
		{
			manager.getTransaction().rollback();
			System.out.println("Não foi possivel cadastrar!!");
		}
		
		
		
	}
		
}
