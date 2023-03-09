package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Veiculo;
import model.Venda;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import repository.VendaRepository;
import repository.VendedorRepository;

public class ClienteService {

	ClienteRepository repository;
	VeiculoRepository veiculoRepository;
	VendaRepository vendaRepository;
	Scanner sc;
	
	public ClienteService(Scanner sc) {
		this.sc = sc;
		this.repository = new ClienteRepository();
		this.veiculoRepository = new VeiculoRepository();
		this.vendaRepository = new VendaRepository();
	}
	
	public Cliente procurarCliente(String cpf) {
		Cliente cliente = this.repository.buscarPorCpf(cpf);
		
		if(cliente == null) {
			return this.criarCliente(cpf); 
		}else {
			return cliente;
		}
		
	}	
	
	public Cliente criarCliente(String cpf) {
		sc.nextLine();
		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
		
		System.out.println("Digite seu endereço:");
		String endereco = sc.nextLine();
		
		System.out.println("Digite sua senha:");
		String senha = sc.next();
		
		Cliente cliente = new Cliente(nome, endereco, cpf, senha);
		
		this.repository.salvar(cliente);
		System.out.println("Cadastrado criado com sucesso!");
		return cliente;
	}
	
	public void alugarVeiculo(Cliente cliente, Veiculo veiculo, int diasAlugado) {
		cliente.getVeiculosAlugados().add(veiculo);
		cliente.setValorDebito(cliente.getValorDebito() + (veiculo.getValorDiario() * diasAlugado));
		
		this.repository.salvar(cliente);
		
		System.out.println("Veiculo alugado com sucesso. Dia da entrega: " 
																+ LocalDate.now().plusDays(diasAlugado)
																.format(DateTimeFormatter.ofPattern("dd/MM/yyy")));
	}
	
	public void devolverVeiculo(Cliente cliente, Veiculo veiculo) {
		Venda venda = this.vendaRepository.buscarPeloClienteEVeiculo(cliente.getId(), veiculo.getId());
		venda.setEntregue(true);
		this.vendaRepository.salvar(venda);
	}
	
	public void mostrarVeiculosEDebitos(Cliente cliente) {
		this.mostrarVeiculosAlugados(cliente);
		System.out.println("Seu total de débito: R$" + cliente.getValorDebito());
		
	}
	
	public void mostrarVeiculosAlugados(Cliente cliente) {
		List<Veiculo> veiculosAlugados = this.veiculoRepository.buscarVeiculosPeloClienteId(cliente.getId());
		
		veiculosAlugados.forEach(veiculo -> System.out.println(veiculo));
	}
}
