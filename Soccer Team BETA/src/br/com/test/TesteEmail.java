package br.com.test;

import java.util.ArrayList;
import java.util.List;
import br.com.project.util.all.EmailUtil;
import br.com.project.util.all.StringUtil;

public class TesteEmail {

	
	 public static void main(String[] args) {
		
		List<String> emails = new ArrayList<String>();
		//emails.add("nyrocha2010@hotmail.com");
		//emails.add("nyrocha2010@hotmail.com");
		//String msg = "Testando o envio de email!";		
		//System.out.println(EmailUtil.enviarEmails(StringUtil.separarPorVirgulas(emails), "Teste" , msg));
		
		System.out.println(EmailUtil.validar("java"));
	
	 }
}