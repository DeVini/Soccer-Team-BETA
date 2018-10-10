package br.com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

import br.com.project.model.enums.TipoTreino;

@Audited
@Entity
@Table(name = "treinos")
@PrimaryKeyJoinColumn(name = "evento_id")
public class Treino extends Evento {

	private static final long serialVersionUID = 1L;

	@Enumerated
	@Column(nullable = false)
	private TipoTreino tipo;


	public TipoTreino getTipo() {
		return tipo;
	}

	public void setTipo(TipoTreino tipo) {
		this.tipo = tipo;
	}
}