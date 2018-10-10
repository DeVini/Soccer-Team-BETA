package br.com.project.model.enums;

public enum TipoSanguineo {

	A_POSITIVO("A+"),
	A_NEGATIVO("A-"),
	B_POSITIVO("B+"),
	B_NEGATIVO("B-"),
	AB_POSITIVO("AB+"),
	AB_NEGATIVO("AB-"),
	O_POSITIVO("O+"),
	O_NEGATIVO("O-");
	
	private String valor;
	
	private TipoSanguineo(String valor){
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor;
	}
}