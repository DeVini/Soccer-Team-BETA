package br.com.project.model.enums;

public enum TipoSaida {
	
	ALUGUEL_QUADRA("Aluguel de Quadra"),
	MATERIAL_ESPORTIVO("Material Esportivo"),
	UNIFORME("Uniforme"),
	INSCRICAO("Inscri��o"),
	DOCUMENTO("Documenta��o"),
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