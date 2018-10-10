package br.com.project.model.enums;

public enum TipoCampeonato {
	
	MATA_MATA("Mata-Mata"),
	PONTOS_CORRIDOS("Pontos Corridos"),
	GRUPO_MATA_MATA("Grupos e Mata-Mata");
	
	private String valor;
	
	private TipoCampeonato(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}