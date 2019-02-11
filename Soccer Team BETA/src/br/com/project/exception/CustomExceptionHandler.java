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
import br.com.project.model.validator.UsernameValidator;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapperd;
	final FacesContext context = FacesContext.getCurrentInstance();
	final Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
	final NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();

	public CustomExceptionHandler(ExceptionHandler w) {
		this.wrapperd = w;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapperd;
	}

	@Override
	public void handle() throws FacesException {
	
		Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event =  iterator.next();
			ExceptionQueuedEventContext contextEvent = (ExceptionQueuedEventContext) event.getSource();

			// Recuperar a exceção do contexto
			Throwable exception = contextEvent.getException();
			
			// Aqui trabalhamos a exceção
			try {
				
				requestMap.put("exceptionMessage", exception.getMessage());
				 if(exception != null && exception.getMessage() != null
							&& exception.getMessage().indexOf("username.email") != -1){
						
						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esse endereço do email já está em uso", ""));
						

						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esse nome de usuario já está em uso", ""));
				}else
				if(exception != null && exception.getMessage() != null
							&& exception.getMessage().indexOf("username") != -1){
						
						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esse nome de usuario já está em uso", ""));
				}
				else if(exception != null && exception.getMessage() != null
							&& exception.getMessage().indexOf("email") != -1){
						
						FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Esse endereço do email já está em uso", ""));
				}
				else
						
				if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Registro não pode ser removido por estar associado.", ""));
					
				} else if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Registro foi atualizado ou excluido por outro usuario. Consulte novamente.", ""));
				}
				
				else {
					
					// avisa o usuario do erro
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"O sistema se recuperou de um erro inesperado.", ""));

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Você pode continuar usando o sistema normalmente.", ""));

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"O erro foi causado por: \n" + exception.getMessage(), ""));

					org.primefaces.context.RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Erro", "O sistema se recuperou de um erro inesperado..."));
					// redirecionamento para pagina de erro
					//navigationHandler.handleNavigation(context, null,
							//"/error/error.jsf?faces-redirect=true&expired=true");
					
					
				}
				//renderiza a pagina de erro e exibe as mensagens
			
				context.renderResponse();

			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				// imprime o erro no console
				//exception.printStackTrace();
				iterator.remove();
			}
		}
		
		
		
		getWrapped().handle();
	}
}