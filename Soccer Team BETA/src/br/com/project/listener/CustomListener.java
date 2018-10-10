package br.com.project.listener;

import java.io.Serializable;
import org.hibernate.envers.RevisionListener;
import br.com.framework.utils.UtilFramework;
import br.com.project.model.InformacaoRevisao;
import br.com.project.model.Usuario;


public class CustomListener implements RevisionListener,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revision) {
		
		InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revision;
		Long codUser = UtilFramework.getThreadLocal().get();
		
		Usuario entidade = new Usuario();
		if(codUser != null && codUser != 0L){
			entidade.setId(codUser);
			informacaoRevisao.setEntidade(entidade);
		}
		
	}
}