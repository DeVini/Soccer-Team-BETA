package br.com.project.geral.controller;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.Time;
import br.com.repository.interfaces.RepositoryTime;

@Controller
public class TimeController extends ImplementacaoCrud<Time> implements RepositoryTime{


	private static final long serialVersionUID = 1L;
	
	public boolean validarNome(String username)  {
		
		boolean retorno;
		String sql = "select count(1) as validaNome from times where nome = ?";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {username});
		if(sqlRowSet.next()){
			if(sqlRowSet.getInt("validaNome") == 1){
				retorno = true;
			}else{
				retorno = false;
			}
		}else{
			retorno = false;
		}
		return  retorno;
	}

}
