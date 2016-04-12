package models;
// default package
// Generated Apr 11, 2016 2:03:20 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pedido generated by hbm2java
 */
@Entity
@Table(name = "pedido", schema = "public")
public class Pedido implements java.io.Serializable {

	private int idPedido;
	private Atendente atendente;
	private Cliente cliente;
	private Date dataEntrega;
	private Date dataLocacao;
	private BigDecimal total;
	private Set<Item> items = new HashSet<Item>();

	public Pedido() {
	}

	public Pedido(int idPedido, Atendente atendente, Cliente cliente, Date dataEntrega, Date dataLocacao,
			BigDecimal total) {
		this.idPedido = idPedido;
		this.atendente = atendente;
		this.cliente = cliente;
		this.dataEntrega = dataEntrega;
		this.dataLocacao = dataLocacao;
		this.total = total;
	}

	public Pedido(int idPedido, Atendente atendente, Cliente cliente, Date dataEntrega, Date dataLocacao,
			BigDecimal total, Set<Item> items) {
		this.idPedido = idPedido;
		this.atendente = atendente;
		this.cliente = cliente;
		this.dataEntrega = dataEntrega;
		this.dataLocacao = dataLocacao;
		this.total = total;
		this.items = items;
	}

	@Id
	@SequenceGenerator(name="seq_pedido", sequenceName="seq_pedido_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_pedido")
	@Column(name = "id_pedido", unique = true, nullable = false)
	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_atendente", nullable = false)
	public Atendente getAtendente() {
		return this.atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrega", nullable = false, length = 29)
	public Date getDataEntrega() {
		return this.dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_locacao", nullable = false, length = 29)
	public Date getDataLocacao() {
		return this.dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	@Column(name = "total", nullable = false, precision = 20, scale = 5)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@OneToMany(mappedBy="pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
