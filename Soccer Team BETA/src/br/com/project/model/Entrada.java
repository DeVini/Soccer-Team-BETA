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
@Table(name = "entradas")
@PrimaryKeyJoinColumn(name = "financeiro_id")
public class Entrada extends Financeiro {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEntrada tipo;
	

	public TipoEntrada getTipo() {
		return tipo;
	}

	public void setTipo(TipoEntrada tipo) {
		this.tipo = tipo;
	}
}