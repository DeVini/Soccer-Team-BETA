package br.com.project.util.all;

public enum EstatusPersistencia {
	
	ERRO("Erro"),SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse Objeto não pode ser apagado por possuir referencias no mesmo");
	
	private String valor;
	
	private EstatusPersistencia(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.valor;
	}
	
}
