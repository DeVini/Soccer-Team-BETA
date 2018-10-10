package br.com.project.model.enums;

public enum Posicao {

	GOLEIRO("Goleiro"),
	ZAGUEIRO("Zagueiro"),
	FIXO("Fixo"),
	LATERAL_ESQUERDO("Lateral Esquerdo"),
	LATERAL_DIREITO("Lateral Direito"),
	ALA_DIREITO("Ala Direito"),
	ALA_ESQUERDO("Ala Esquerdo"),
	PRIMEIRO_VOLANTE("Primeiro Volante"),
	SEGUNDO_VOLANTE("Segundo Volante"),
	MEIO_CAMPO_CENTRAL("Meio Campo Central"),
	MEIO_DIREITO("Meio Direito"),
	MEIO_ESQUERDO("Meio Esquerdo"),
	MEIO_ATACANTE("Meio Atacante"),
	ATACANTE("Atacante"),
	PONTA_ESQUERDO("Ponta Esquerdo"),
	PONTA_DIREITO("Ponta Direito"),
	CENTRO_AVANTE("Centro Avante"),
	PIVO("Pivô");
	
	private String valor;
	
	private Posicao(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}