package br.com.project.listener;

import java.io.Serializable;
import org.hibernate.envers.RevisionListener;
import br.com.framework.utils.UtilFramework;

public class CustomListener implements RevisionListener,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revision) {
		
		Long codUser = UtilFramework.getThreadLocal().get();
		
	}
}