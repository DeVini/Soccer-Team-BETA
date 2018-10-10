package br.com.project.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.envers.Audited;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.util.all.Imagem;

@Audited
@Entity
@Table(name = "ligas")
public class Liga implements Serializable{

	private static final long serialVersionUID = 1L;
	@IdentificaCampoPesquisa(campoConsulta = "id" , descricaoCampo = "Código")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@IdentificaCampoPesquisa(campoConsulta = "nome" , descricaoCampo = "Nome da Liga", principal = 1)
	@Column(nullable = false)
	private String nome;
	
	private String email;
	@Transient
	private Imagem emblema;
	
	private String site;
	
	@ManyToMany
	@JoinTable(name = "liga_contato", joinColumns = @JoinColumn(name = "liga_id"),
	inverseJoinColumns = @JoinColumn(name = "contato_id"))
	private Set<Contato> contatos;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Imagem getEmblema() {
		return emblema;
	}

	public void setEmblema(Imagem emblema) {
		this.emblema = emblema;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Set<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
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
		Liga other = (Liga) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}