package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.Cliente;
import model.Veiculo;
import repository.ClienteRepository;

public class ClienteService {

	ClienteRepository repository;
	Scanner sc;
	
	public ClienteService(Scanner sc) {
		this.sc = sc;
		this.repository = new ClienteRepository();
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
		cliente.getVeiculosAlugados().remove(veiculo);
		
		this.repository.salvar(cliente);
	}
	
	public void mostrarVeiculosEDebitos(Cliente cliente) {
		this.mostrarVeiculosAlugados(cliente);
		System.out.println("Seu total de débito: R$" + cliente.getValorDebito());
		
	}
	
	public void mostrarVeiculosAlugados(Cliente cliente) {
		cliente = this.repository.buscarPorId(cliente.getId());
		cliente.getVeiculosAlugados().forEach(veiculo -> System.out.println(veiculo));
	}
}
