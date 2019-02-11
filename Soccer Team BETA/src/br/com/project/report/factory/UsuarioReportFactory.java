package br.com.project.report.factory;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.HibernateException;

import br.com.project.bean.view.UsuarioBeanView;
import br.com.project.model.Usuario;

public class UsuarioReportFactory {
	
	
	private static UsuarioBeanView user = new UsuarioBeanView(); 

	public static List<Usuario> getList() throws HibernateException, Exception{
		
		return user.getList();
		
	}
	

}
