package br.com.framework.implementacao.crud;

import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

/*
 * 
 * Realizar Consultas SQL
 * 
 */

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JdbcTemplateImple extends JdbcTemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public JdbcTemplateImple(DataSource dataSource){
		super(dataSource);
	}
	
	
}
