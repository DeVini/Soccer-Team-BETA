package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.model.Time;
import br.com.project.model.Usuario;

public interface RepositoryUsuario extends Serializable  {

	
	public List<Usuario> getUsers(Time time) throws HibernateException, Exception;
	Date getUltimoAcessoEntidadeLogada(String name);
	void updateUltimoAcessoUser(String login);
}
