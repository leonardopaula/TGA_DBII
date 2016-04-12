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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Carro generated by hbm2java
 */
@Entity
@Table(name = "carro", schema = "public")
public class Carro implements java.io.Serializable {

	private int idCarro;
	private Categoria categoria;
	private String marca;
	private String modelo;
	private String cor;
	//private Set<Item> items = new HashSet<Item>(0);

	public Carro() {
	}

	public Carro(int idCarro, Categoria categoria, String marca, String modelo, String cor) {
		this.idCarro = idCarro;
		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
	}

	public Carro(int idCarro, Categoria categoria, String marca, String modelo, String cor,
			Set<Item> items) {
		this.idCarro = idCarro;
		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		//this.items = items;
	}

	@Id
	@SequenceGenerator(name="seq_carro", sequenceName="seq_carro_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_carro")
	@Column(name = "id_carro", unique = true, nullable = false)
	public int getIdCarro() {
		return this.idCarro;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "marca", nullable = false, length = 50)
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column(name = "modelo", nullable = false, length = 50)
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "cor", nullable = false, length = 20)
	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "carro")
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}*/

}
