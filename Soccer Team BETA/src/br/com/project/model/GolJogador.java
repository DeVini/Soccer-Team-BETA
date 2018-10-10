package br.com.project.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "gol_jogador")
public class GolJogador implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Basic
	@ManyToOne
	@JoinColumn(name = "jogador", nullable=false)
	@ForeignKey(name = "jogador_fk")
	private Jogador jogador;
	@Basic
	@ManyToOne
	@JoinColumn(name = "jogo", nullable=false)
	@ForeignKey(name = "jogo_fk")
	private Jogo jogo;
	@Column(nullable = false)
	private int gols;
	
	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public int getGols() {
		return gols;
	}
	public void setGols(int gols) {
		this.gols = gols;
	}
	public int getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((jogador == null) ? 0 : jogador.hashCode());
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GolJogador other = (GolJogador) obj;
		if (id != other.id)
			return false;
		if (jogador == null) {
			if (other.jogador != null)
				return false;
		} else if (!jogador.equals(other.jogador))
			return false;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}
	
}