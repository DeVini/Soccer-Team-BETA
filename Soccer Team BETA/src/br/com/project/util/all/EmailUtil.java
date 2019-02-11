package br.com.project.util.all;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static String HOST = "smtp.gmail.com";
	private static String SOCKET_PORT = "465";
	private static String CLASS = "javax.net.ssl.SSLSocketFactory";
	private static String AUTH = "true";
	private static String PORT = "465";
	
	private static String EMAIL_REMETENTE = "nyrocha018@gmail.com";
	private static String PASSWORD = "mvm45678";
	
	
	public static boolean validar(String email){
		boolean retorno = false;
		try 
		{
			InternetAddress address = new InternetAddress(email);
			address.validate();
			retorno = true;
			
		} catch (AddressException e) {
			retorno = false;
		}
		return retorno;
	}
	
	public static String enviarEmails(String destinatarios, String assunto, String msg){
		
		String retorno = "";
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host",HOST);
		props.put("mail.smtp.socketFactory.port",SOCKET_PORT);
		props.put("mail.smtp.socketFactory.class",CLASS);
		props.put("mail.smtp.auth",AUTH);
		props.put("mail.smtp.port",PORT);

		Session session = Session.getDefaultInstance(props,
	                new javax.mail.Authenticator() {
	                     protected PasswordAuthentication getPasswordAuthentication() 
	                     {
	                           return new PasswordAuthentication(EMAIL_REMETENTE, PASSWORD);
	                     }
	                });

	    /** Ativa Debug para sessão */
	    session.setDebug(true);

	    try {

			Message message = new MimeMessage(session);
			InternetAddress address = new InternetAddress(EMAIL_REMETENTE);
		
			message.setFrom(address); // Remetente
			
			Address[] toUser = InternetAddress // Destinatário(s)
					.parse(destinatarios);
	
			message.setRecipients(Message.RecipientType.TO,toUser);
			message.setSubject(assunto);// Assunto
			message.setText(msg);
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			retorno = "Feito!!!";

		}catch(
		MessagingException e)
		{
			retorno = "Erro!!!";
			throw new RuntimeException(e);
			
		}
	    
	    return retorno;
	}
	
}