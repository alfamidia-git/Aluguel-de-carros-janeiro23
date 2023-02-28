package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Venda;
import model.Vendedor;
import repository.VendaRepository;
import repository.VendedorRepository;

public class VendedorService {

	private VendedorRepository repository;
	private VendaRepository vendaRepository;
	private Scanner sc;
	
	public VendedorService(Scanner sc) {
		this.sc = sc;
		this.repository = new VendedorRepository();
		this.vendaRepository = new VendaRepository();
	}
	
	public void mostrarTodosVendedores() {
		this.repository.buscarTodos().forEach(vendedor -> System.out.println(vendedor));
	}
	
	public void adicionarVenda(int idVendedor, Veiculo veiculo, int diasAlugado, Cliente cliente) {
		Vendedor vendedor = this.repository.buscarPorId(idVendedor);
		
		if(vendedor == null) {
			throw new SistemaException("Vendedor não localizado!");
		}
		
		double comissao = (veiculo.getValorDiario() * diasAlugado) * Vendedor.BONIFICACAO;
		
		vendedor.setComissao(vendedor.getComissao() + comissao);
		vendedor.addVendas();
		
		this.vendaRepository.salvar(new Venda(vendedor, veiculo, cliente));
		this.repository.salvar(vendedor);
	}

	public Vendedor procurarVendedor(String cpf) {
		List<Vendedor> vendedores = this.repository.buscarTodos();
		
		return vendedores.stream().filter(vendedor -> vendedor.getCpf().equals(cpf)).findFirst().orElse(null);
	}

	public void mostrarDetalhesDoVendedor(Vendedor vendedor) {
		System.out.println("Seu total de vendas é: " + vendedor.getVendas());
		System.out.println("Seu salário + comissão é: R$" + (vendedor.getSalario() + vendedor.getComissao()));
		
	}
	
	public void mostrarVendasDoVendedor(Vendedor vendedor) {
		List<Venda> todasVendas = this.vendaRepository.buscarTodos();
		
		todasVendas.removeIf(venda -> venda.getVendedor().getId() != vendedor.getId());
		
		todasVendas.forEach(venda -> System.out.println(venda));
	}
	
	public void criarNovoVendedor() {
		sc.nextLine();
		System.out.println("Digite o nome: ");
		String nome = sc.nextLine();
		
		System.out.println("Digite o endereço:");
		String endereco = sc.nextLine();
		
		System.out.println("Digite o cpf:");
		String cpf = sc.next();
		
		System.out.println("Digite a senha:");
		String senha = sc.next();
		
		System.out.println("Digite o salário:");
		double salario = sc.nextDouble();
		
		Vendedor vendedor = new Vendedor(nome, endereco, cpf, senha, salario);
		
		this.repository.salvar(vendedor);
	}

	public void removerUmVendedor(int opcaoVendedor) {
		this.repository.remover(opcaoVendedor);		
	}
}
