package br.com.dao.implementacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.Time;
import br.com.project.model.Usuario;
import br.com.repository.interfaces.RepositoryLogin;
import br.com.repository.interfaces.RepositoryUsuario;

@Repository
public class DaoUsuario extends ImplementacaoCrud<Object> implements RepositoryUsuario  {

	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsers(Time time) throws HibernateException, Exception {
		List<Usuario> list = new ArrayList<Usuario>();
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select distinct(entity) from ").append(Usuario.class.getSimpleName()).append(" entity ")
		.append("where time = :param");
		list = getSession().createQuery(query.toString()).setLong("param", time.getId()).list();
		executeFlushSession();
		return list;
	}


	
	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
	
		SqlRowSet rowSet = super.getJdbcTemplate().
				queryForRowSet("select ultimoacesso from usuarios username = ?",
						new Object[]{name});
		
		return rowSet.next() ? rowSet.getDate("ultimoascesso") : null;
	}


	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = 
				"update usuarios set ultimoacesso = current_timestamp where username  = ?";
		super.getSimpleJdbcTemplate().update(sql, login);
	}


}