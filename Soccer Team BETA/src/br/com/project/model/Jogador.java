package br.com.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.util.all.Imagem;

@Audited
@Entity
@Table(name = "jogadores")
public class Jogador implements Serializable{

	private static final long serialVersionUID = 1L;
	@IdentificaCampoPesquisa(campoConsulta = "id" , descricaoCampo = "Código")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Transient
	private Imagem foto;
	@IdentificaCampoPesquisa(campoConsulta = "nome" , descricaoCampo = "Nome" , principal = 1)
	@Column(nullable = false, length = 45,unique = true)
	private String nome;
	@IdentificaCampoPesquisa(campoConsulta = "cpf" , descricaoCampo = "CPF")
	@Column(nullable = false, length = 11)
	private long cpf;
	@IdentificaCampoPesquisa(campoConsulta = "dtNascimento" , descricaoCampo = "Data de Nascimento")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dtNascimento;
	@Column(nullable = false)
	private float peso;
	@Column(nullable = false)
	private float altura;
	@Enumerated(EnumType.STRING)
	private TipoSanguineo tipoSanguineo;
	
	private double salario;
	
	private String descricao;
	
	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time",nullable = false)
	@ForeignKey(name = "time_fk2")
	private Time time = new Time();	
	
	private int numero;
	
	@ElementCollection(targetClass = Posicao.class) 
	@CollectionTable(name = "jogador_posicao",
	    joinColumns = @JoinColumn(name = "jogador_id"))
	@Enumerated(EnumType.STRING)
	private Set<Posicao> posicoes;
	@ManyToMany
	@JoinTable(name = "jogador_contato", joinColumns = @JoinColumn(name = "jogador_id"),
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

	public Imagem getFoto() {
		return foto;
	}

	public void setFoto(Imagem foto) {
		this.foto = foto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public void setPosicoes(Posicao posicao) {
		this.posicoes.add(posicao);
	}

	public void setContatos(Contato contato) {
		this.contatos.add(contato);
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
		Jogador other = (Jogador) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}