package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Venda;

public class VendaRepository {

Map<Integer, Venda> repository;
	
	public VendaRepository() {
		this.repository = new HashMap<>();
	}
	
	public void salvar(Venda venda) {
		this.repository.put(venda.getId(), venda);
	}
	
	public void remover(Integer id) {
		this.repository.remove(id);
	}
	
	public List<Venda> buscarTodos(){
		return this.repository.values().stream().collect(Collectors.toList());
	}
	
	public Venda buscarPorId(Integer id) {
		return this.repository.get(id);
	}
}
