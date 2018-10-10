package br.com.project.model.enums;

public enum TipoTreino {
	
	FISICO("Fisico"),
	FINALIZACAO("Finalização"),
	PASSE("Passe"),
	CRUZAMENTO("Cruzamento"),
	BOLAS_PARADAS("Bola Parada"),
	TEORICO("Teorico"),
	ACADEMIA("Academia"),
	POSICIONAMENTO("Posicionamento"),
	DEFESA("Defesa"),
	ATAQUE("Ataque"),
	AGILIDADE("Agilidade"),
	CABECEIO("Cabeceio"),
	MARCACAO("Marcação"),
	COLETIVO("Coletivo"),
	COMPLETO("Completo");
	
	private String valor;
	
	private TipoTreino(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}