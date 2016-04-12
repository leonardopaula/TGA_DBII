package models;
// default package
// Generated Apr 11, 2016 2:03:20 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Atendente generated by hbm2java
 */
@Entity
@Table(name = "atendente", schema = "public")
public class Atendente implements java.io.Serializable {

	private int idAtendente;
	private String nome;
	private String login;
	private int senha;
	private Set<Pedido> pedidos = new HashSet<Pedido>(0);

	public Atendente() {
	}

	public Atendente(int idAtendente, String nome, String login, int senha) {
		this.idAtendente = idAtendente;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public Atendente(int idAtendente, String nome, String login, int senha, Set<Pedido> pedidos) {
		this.idAtendente = idAtendente;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.pedidos = pedidos;
	}

	@Id
	@SequenceGenerator(name="seq_atendente", sequenceName="seq_atendente_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_atendente")
	@Column(name = "id_atendente", unique = true, nullable = false)
	public int getIdAtendente() {
		return this.idAtendente;
	}

	public void setIdAtendente(int idAtendente) {
		this.idAtendente = idAtendente;
	}

	@Column(name = "nome", nullable = false, length = 100)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "login", nullable = false, length = 50)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "senha", nullable = false)
	public int getSenha() {
		return this.senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atendente")
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}