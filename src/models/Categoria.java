package models;
// default package
// Generated Apr 11, 2016 2:03:20 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
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

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name = "categoria", schema = "public")
public class Categoria implements java.io.Serializable {

	private int idCategoria;
	private String descricao;
	private BigDecimal valor;
	private Set<Carro> carros = new HashSet<Carro>(0);

	public Categoria() {
	}

	public Categoria(int idCategoria, String descricao, BigDecimal valor) {
		this.idCategoria = idCategoria;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Categoria(int idCategoria, String descricao, BigDecimal valor, Set<Carro> carros) {
		this.idCategoria = idCategoria;
		this.descricao = descricao;
		this.valor = valor;
		this.carros = carros;
	}

	@Id
	@SequenceGenerator(name="seq_categoria", sequenceName="seq_categoria_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_categoria")
	@Column(name = "id_categoria", unique = true, nullable = false)
	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Column(name = "descricao", nullable = false, length = 50)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "valor", nullable = false, precision = 20, scale = 5)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Carro> getCarros() {
		return this.carros;
	}

	public void setCarros(Set<Carro> carros) {
		this.carros = carros;
	}

}