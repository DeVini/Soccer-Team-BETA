package br.com.srv.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import br.com.project.model.Time;
import br.com.project.model.Usuario;

@Service
public interface SrvUsuario extends Serializable {

	public List<Usuario> getUsers(Time time) throws HibernateException, Exception;
	Date getUltimoAcessoEntidadeLogada(String name);
	void updateUltimoAcessoUser(String login);
	
}
