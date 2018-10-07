package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.hibernate.SessionFactory;
import br.com.framework.hibernate.session.HibernateUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;
	final FacesContext context = FacesContext.getCurrentInstance();
	final Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
	final NavigationHandler navigation = context.getApplication().getNavigationHandler();
	
	public CustomExceptionHandler(ExceptionHandler w) {
		this.wrapped = w;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}
	
	@Override
	public void handle() throws FacesException {
		
		final Iterator<ExceptionQueuedEvent> iterator = getHandledExceptionQueuedEvents().iterator();
		while(iterator.hasNext()){
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext contextEvent =  (ExceptionQueuedEventContext) event.getSource();
			
			Throwable exception = contextEvent.getException();
			try {
				requestMap.put("exceptionMessage", exception.getMessage());

				if (exception != null && exception.getMessage() != null) {

					if (exception.getMessage().indexOf("ConstraintViolationException") != -1) {

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Registro não pode ser removido por estar associado", ""));

					} else if (exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Registro foi atualizado ou excluido por outro usuario. Consulte novamente", ""));
					} else {

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"O sistema se recuperou de um erro inesperado", ""));

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Você pode continuar usando o sistema normalmente", ""));

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"O erro foi causado por: \n"+ exception.getMessage(), ""));
					}
					this.context.renderResponse();
				}

			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}

				exception.printStackTrace();
				iterator.remove();
			}
			getWrapped().handle();
		}
	}
}