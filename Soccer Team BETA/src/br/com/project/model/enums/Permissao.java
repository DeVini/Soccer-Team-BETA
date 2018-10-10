package br.com.project.model.enums;

public enum Permissao {
	
	ADMIN("Administrador"),
	USER("Usuario Simples"),
	GERENTE("Gerente"),
	COMISSAO_TECNICA("Comiss�o T�cnica");
	
	private String valor;
	
	private Permissao(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}