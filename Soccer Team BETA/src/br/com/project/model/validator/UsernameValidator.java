package br.com.project.model.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.Usuario;


public class UsernameValidator extends ImplementacaoCrud<Usuario> implements Validator {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		this.username = (String) value;
		
		String sql = "select count(1) as autentica from usuarios where username = ?;";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {this.username});
		boolean retorno = sqlRowSet.next() ? sqlRowSet.getObject("autentica") != null : false;
		if(retorno){
			throw new IllegalArgumentException("username ja existe");
		}
	}
	

	public boolean valid(String username)  {
		
		
		String sql = "select count(1) as autentica from usuarios where username = ?;";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {username});
		return  sqlRowSet.next() ? sqlRowSet.getObject("autentica") != null : false;
		
	}

}
