package br.com.project.model.enums;

import java.util.ArrayList;
import java.util.List;
//import javax.faces.bean.ManagedBean;


//@ManagedBean(name="permissaoService", eager = true)
public enum Permissao {
	
	ADMIN("Administrador"),
	USER("Usuario Simples"),
	GERENTE("Gerente"),
	COMISSAO_TECNICA("Comissão Técnica");
	
	private String valor;
	
	private Permissao(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
	
	public static List<Permissao> getPermissoes(){
		
		List<Permissao> list = new ArrayList<Permissao>();
		for(Permissao p : Permissao.values()){
			list.add(p);
		}
		return list;
	}
}