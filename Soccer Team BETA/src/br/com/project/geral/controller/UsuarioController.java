package br.com.project.geral.controller;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.Time;
import br.com.project.model.Usuario;
import br.com.project.util.all.EmailUtil;
import br.com.srv.interfaces.SrvUsuario;


@Controller
public class UsuarioController extends ImplementacaoCrud<Usuario> implements InterfaceCrud<Usuario>{

	private static final long serialVersionUID = 1L;

	@Autowired
	private SrvUsuario srvUsuario;

	
	
	public Usuario findUserLogado(String userLogado) throws Exception{
		return super.findIniqueByProperty(Usuario.class, userLogado, "username","");
	}
	
	public List<Usuario> getUsers(Time time) throws HibernateException, Exception{
		return srvUsuario.getUsers(time);
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login){
		return srvUsuario.getUltimoAcessoEntidadeLogada(login);
	}
	
	public void updateUltimoAcessoUser(String name) {
	
		srvUsuario.updateUltimoAcessoUser(name);
		
	}
	
	public void valida(String username)  {
	
		
		String sql = "select count(1) as validaUsername from usuarios where username = ?";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {username});
		
		//return  sqlRowSet.next() ? sqlRowSet.getObject("validaUsername") != null : false;
		if(sqlRowSet.next()){
			System.out.println(sqlRowSet.getObject("validaUsername"));
		}
		
	}
	
	public boolean valid(String username)  {
	
		boolean retorno;
		String sql = "select count(1) as validaUsername from usuarios where username = ?";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {username});
		if(sqlRowSet.next()){
			if(sqlRowSet.getInt("validaUsername") == 1){
				retorno = true;
			}else{
				retorno = false;
			}
		}else{
			retorno = false;
		}
		return  retorno;
	}
	
	public boolean validEmail(String email)  {
	
		boolean retorno;
		String sql = "select count(1) as validaEmail from usuarios where email = ?";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {email});
		if(sqlRowSet.next()){
			if(sqlRowSet.getInt("validaEmail") == 1){
				retorno = true;
			}else{
				retorno = false;
			}
		}else{
			retorno = false;
		}
		return  retorno;	
	}
	
	public void  boasVindas(Usuario usuario){
		StringBuffer sb = new StringBuffer();
		sb.append("Olá ").append(usuario.getUsername()).append("!! ")
		.append("Seja bem vindo ao sistema Soccer Team !");
		
		EmailUtil.enviarEmails(usuario.getEmail(),"SoccerTeam", sb.toString());
	}
	
	
	public void dadosAtualizados(Usuario usuario){
		StringBuffer sb = new StringBuffer();
		sb.append("Olá ").append(usuario.getUsername()).append("!! ")
		.append("Seus dados foram atualizados");
		
		EmailUtil.enviarEmails(usuario.getEmail(),"SoccerTeam", sb.toString());
	}
	
	
	
	public void esqueciSenha(Usuario usuario){
		StringBuffer sb = new StringBuffer();
		sb.append("Username: ").append(usuario.getUsername())
		.append("<br>Senha: ").append(usuario.getPassword());
		
		EmailUtil.enviarEmails(usuario.getEmail(),"SoccerTeam", sb.toString());
	}
	
	/*public Date getUltimoAcessoEntidadeLogada(String login){
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}
	
	public void updateUltimoAcessoUser(String name) {
	*
		srvEntidade.updateUltimoAcessoUser(name);
		
	}*/
}