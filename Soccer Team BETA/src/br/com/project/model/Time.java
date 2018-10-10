package br.com.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.envers.Audited;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.util.all.Imagem;

@Audited
@Entity
@Table(name = "times")
public class Time implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(campoConsulta = "id" , descricaoCampo = "Código")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_time")
	private long id;
	@IdentificaCampoPesquisa(campoConsulta = "nome" , descricaoCampo = "Nome do Time",principal = 1)
	@Column(nullable = false,length = 45, unique = true)
	private String nome;
	@Transient
	private Imagem emblema;
	@Column(nullable=true, length = 30)
	private String localTreino;
	@Column(nullable=true, length = 30)
	private String mandoDeCampo;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dtFundacao;
	
	@Transient
	private List<Uniforme> uniformes = new ArrayList<Uniforme>();

	@Version
	@Column(name = "versionNum" )
	private int versionNum;
	
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
	public Imagem getEmblema() {
		return emblema;
	}
	public void setEmblema(Imagem emblema) {
		this.emblema = emblema;
	}
	public String getLocalTreino() {
		return localTreino;
	}
	public void setLocalTreino(String localTreino) {
		this.localTreino = localTreino;
	}
	public String getMandoDeCampo() {
		return mandoDeCampo;
	}
	public void setMandoDeCampo(String mandoDeCampo) {
		this.mandoDeCampo = mandoDeCampo;
	}
	public Date getDtFundacao() {
		return dtFundacao;
	}
	public void setDtFundacao(Date dtFundacao) {
		this.dtFundacao = dtFundacao;
	}
	public List<Uniforme> getUniformes() {
		return uniformes;
	}
	public void setUniformes(List<Uniforme> uniformes) {
		this.uniformes = uniformes;
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
		Time other = (Time) obj;
		if (id != other.id)
			return false;
		return true;
	}
}