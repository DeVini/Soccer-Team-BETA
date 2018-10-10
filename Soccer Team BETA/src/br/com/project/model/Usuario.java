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
import br.com.project.model.enums.Permissao;

@Audited
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@IdentificaCampoPesquisa(campoConsulta = "id" , descricaoCampo = "Código")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_Usuario")
	private long id;
	@IdentificaCampoPesquisa(campoConsulta = "username" , descricaoCampo = "Username", principal = 1)
	@Column(nullable = false, length = 15, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@IdentificaCampoPesquisa(campoConsulta = "email" , descricaoCampo = "Email")
	@Column(nullable = false, unique = true)
	private String email;
	@IdentificaCampoPesquisa(campoConsulta = "permissao" , descricaoCampo = "Permissão")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Permissao permissao;

	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time",nullable = false)
	@ForeignKey(name = "time_fk3")
	private Time time = new Time();
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}