package br.com.project.geral.controller;

import java.util.HashMap;
import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionControllerImpl implements SessionController {

	private static final long serialVersionUID = 1L;

	private HashMap<String, HttpSession> map = new HashMap<String,HttpSession>();
	
	@Override
	public void addSession(String keyUserLogin, HttpSession session) {
		this.map.put(keyUserLogin, session);
	}

	@Override
	public void invalidateSession(String keyUserLogin) {
		
		HttpSession session = this.map.get(keyUserLogin);
		if(session != null){
			try{
				session.invalidate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("Não tem usuario");
		}
		map.remove(keyUserLogin);
		
	}

}
