package br.com.project.model.enums;

public enum TipoEntrada {

	PREMIO("Premiação"),
	PATROCINIO("Patrocinio"),
	INVESTIMENTO("Investimento"),
	MENSALIDADE("Mensalidade"),
	OUTROS("Outros");
	
	private String valor;

	private TipoEntrada(String valor){
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}
}