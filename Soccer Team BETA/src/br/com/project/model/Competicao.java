package br.com.project.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "competicoes")
public class Competicao implements Serializable {

	private static final long serialVersionUID = 1L;
	@IdentificaCampoPesquisa(campoConsulta = "id" , descricaoCampo = "Código")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@IdentificaCampoPesquisa(campoConsulta = "id" , descricaoCampo = "Código",principal = 1)
	@Column(nullable = false)
	private String nome;
	@Enumerated(EnumType.STRING)
	private TipoCampeonato tipo;
	
	private double premio;
	
	private String site;

	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ligas", nullable=false)
	@ForeignKey(name = "liga_fk")
	private Liga liga = new Liga();
	
	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoCampeonato getTipo() {
		return tipo;
	}
	public void setTipo(TipoCampeonato tipo) {
		this.tipo = tipo;
	}
	public double getPremio() {
		return premio;
	}
	public void setPremio(double premio) {
		this.premio = premio;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Liga getLiga() {
		return liga;
	}
	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((liga == null) ? 0 : liga.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Competicao other = (Competicao) obj;
		if (id != other.id)
			return false;
		if (liga == null) {
			if (other.liga != null)
				return false;
		} else if (!liga.equals(other.liga))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}