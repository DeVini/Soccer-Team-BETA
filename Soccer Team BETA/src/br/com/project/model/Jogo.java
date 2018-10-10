package br.com.project.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "jogos")
@PrimaryKeyJoinColumn(name = "evento_id")
public class Jogo extends Evento {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false , length = 25)
	private String adversario;
	
	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competicoes", nullable=true)
	@ForeignKey(name = "competicao_fk")
	private Competicao competicao = new Competicao();
	
	private int placarAdversario ;
	

	
	public String getAdversario() {
		return adversario;
	}

	public void setAdversario(String adversario) {
		this.adversario = adversario;
	}

	public Competicao getCompeticao() {
		return competicao;
	}

	public void setCompeticao(Competicao competicao) {
		this.competicao = competicao;
	}

	public int getPlacarAdversario() {
		return placarAdversario;
	}

	public void setPlacarAdversario(int placarAdversario) {
		this.placarAdversario = placarAdversario;
	}
}