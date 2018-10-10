package br.com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "saidas")
@PrimaryKeyJoinColumn(name = "financeiro_id")
public class Saida extends Financeiro{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoSaida tipo;
	
	
	public TipoSaida getTipo() {
		return tipo;
	}

	public void setTipo(TipoSaida tipo) {
		this.tipo = tipo;
	}
}