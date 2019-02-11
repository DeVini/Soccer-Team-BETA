package br.com.srv.implementacao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.model.Time;
import br.com.project.model.Usuario;
import br.com.repository.interfaces.RepositoryUsuario;
import br.com.srv.interfaces.SrvUsuario;


public class SrvUsuarioImpl implements SrvUsuario {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryUsuario repositoryUsuario;

	@Override
	public List<Usuario> getUsers(Time time) throws HibernateException, Exception {
		return repositoryUsuario.getUsers(time);
	}

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		return repositoryUsuario.getUltimoAcessoEntidadeLogada(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		repositoryUsuario.updateUltimoAcessoUser(login);
	}

}