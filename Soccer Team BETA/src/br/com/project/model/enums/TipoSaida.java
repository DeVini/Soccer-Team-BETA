package br.com.project.model.enums;

public enum TipoSaida {
	
	ALUGUEL_QUADRA("Aluguel de Quadra"),
	MATERIAL_ESPORTIVO("Material Esportivo"),
	UNIFORME("Uniforme"),
	INSCRICAO("Inscrição"),
	DOCUMENTO("Documentação"),
	IMPOSTO("Imposto"),
	OUTROS("Outros");
	
	private String valor;
	
	private TipoSaida(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}

}