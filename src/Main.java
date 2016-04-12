import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import models.*;

public class Main {

	public static void main(String[] args) {

		String[] categorias = {"Sedan", "Passeio", "Luxo", "Executivo", "Corrida"};
		String[] marcas     = {"GM", "Volkswagem", "Fiat", "GM", "Volkswagem"};
		String[] cores      = {"GM", "Volkswagem", "Fiat", "GM", "Volkswagem"};
		String[] modelos    = {"Corsa", "Gol", "Palio", "Celta", "Fusca"};

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadora");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Preparando atendentes...");
		List<Atendente> la = new ArrayList<Atendente>();
		Atendente atendente;
		for(int i =0; i < 5; i++)
		{
			atendente = new Atendente();
			atendente.setNome("Atendente " + i);
			atendente.setLogin("atendente_" + i);
			atendente.setSenha(123 + i);
			la.add(atendente);
		}
		System.out.println("Salvando atendentes...");
		em.getTransaction().begin();
		for (int i = 0; i < la.size(); i++)
		{
			em.persist(la.get(i));
		}
		em.getTransaction().commit();
		System.out.println("OK atendentes...");

		System.out.println("Preparando categorias...");
		List<Categoria> ca = new ArrayList<Categoria>();
		Categoria categoria;
		for(int i =0; i < 5; i++)
		{
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoria.setValor(new BigDecimal(10.0*(i+1)));
			ca.add(categoria);
		}
		System.out.println("Salvando categorias...");
		em.getTransaction().begin();
		for (int i = 0; i < la.size(); i++)
		{
			em.persist(ca.get(i));
		}
		em.getTransaction().commit();
		System.out.println("OK categorias...");
		
		System.out.println("Preparando clientes...");
		List<Cliente> cli = new ArrayList<Cliente>();
		Cliente cliente;
		for(int i =0; i < 5; i++)
		{
			cliente = new Cliente();
			cliente.setNome("Cliente " + i);
			cliente.setCpf("123.456.789-1" + i);
			cliente.setDtNascimento(new Date("22/01/1900"));
			cli.add(cliente);
		}
		System.out.println("Salvando clientes...");
		em.getTransaction().begin();
		for (int i = 0; i < cli.size(); i++)
		{
			em.persist(cli.get(i));
		}
		em.getTransaction().commit();
		System.out.println("OK clientes...");
		
		System.out.println("Preparando carros...");
		List<Carro> car = new ArrayList<Carro>();
		Carro carro;
		for(int i =0; i < 5; i++)
		{
			carro = new Carro();
			carro.setCor(cores[i]);
			carro.setModelo(modelos[i]);
			carro.setMarca(marcas[i]);
			carro.setCategoria(ca.get(i));
			car.add(carro);
		}
		System.out.println("Salvando carros...");
		em.getTransaction().begin();
		for (int i = 0; i < car.size(); i++)
		{
			em.persist(car.get(i));
		}
		em.getTransaction().commit();
		System.out.println("OK carros...");

		Pedido pedido = new Pedido();
		pedido.setAtendente(la.get(0));
		pedido.setCliente(cli.get(0));
		pedido.setDataEntrega(new Date("11/04/2016"));
		pedido.setDataLocacao(new Date("10/04/2016"));
		pedido.setTotal(new BigDecimal(2000));
		
		System.out.println("Preparando pedidos...");
		Item item = new Item();
		item.setCarro(car.get(0));
		item.setDesconto(new BigDecimal(0));
		item.setValor(new BigDecimal(2000));
		item.setPedido(pedido);
		
		Item item2 = new Item();
		item2.setCarro(car.get(1));
		item2.setDesconto(new BigDecimal(1000));
		item2.setValor(new BigDecimal(3000));
		item2.setPedido(pedido);
		
		em.getTransaction().begin();
			em.persist(pedido);
			em.persist(item);
			em.persist(item2);
		em.getTransaction().commit();
		System.out.println("Ok pedidos...");
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectMapper xmlmapper = new XmlMapper();
		
		TypedQuery<Atendente> qat = em.createQuery("SELECT a FROM Atendente a", Atendente.class);
		la = qat.getResultList();

		TypedQuery<Carro> qcar = em.createQuery("SELECT c FROM Carro c", Carro.class);
		car = qcar.getResultList();

		TypedQuery<Categoria> qcat = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		ca = qcat.getResultList();
		
		TypedQuery<Pedido> qped = em.createQuery("from Pedido p", Pedido.class);
		//List<Pedido> p = qped.getResultList();
		
		Pedido p = qped.getSingleResult();//em.find(Pedido.class, 1);

		Set<Item> its = p.getItems();
		for(int i = 0; i < its.size(); i++)
		{
			System.out.println("=> " + its.toString());
		}

		try {
			mapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/atendentes.json"), la);
			xmlmapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/atendentes.xml"), la);
			mapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/categorias.json"), ca);
			xmlmapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/categorias.xml"), ca);
			mapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/carros.json"), car);
			xmlmapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/carros.xml"), car);
			mapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/pedidos.json"), p);
			xmlmapper.writeValue(new File("/home/leonardo/workspace/TGA_BDII/gerado/pedidos.xml"), p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		em.close();

	}

}
