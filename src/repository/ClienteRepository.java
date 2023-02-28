package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cliente;

public class ClienteRepository {

	private Map<Integer, Cliente> repository;
	
	public ClienteRepository() {
		this.repository = new HashMap<>();
		Cliente cliente = new Cliente("Marlon", "Rua 1", "12345", "12345");
		this.repository.put(cliente.getId(), cliente);
	}
	
	public void salvar(Cliente cliente) {
		this.repository.put(cliente.getId(), cliente);
	}
	
	public void remover(Integer id) {
		this.repository.remove(id);
	}
	
	public List<Cliente> buscarTodos(){
		return this.repository.values().stream().collect(Collectors.toList());
	}
	
	public Cliente buscarPorId(Integer id) {
		return this.repository.get(id);
	}
	
	public Cliente buscarPorCpf(String cpf) {
		return this.buscarTodos().stream().filter(cliente -> cliente.getCpf().equals(cpf)).findFirst().orElse(null);
	}
}
