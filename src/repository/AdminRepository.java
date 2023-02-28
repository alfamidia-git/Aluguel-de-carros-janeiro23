package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Admin;

public class AdminRepository {

Map<Integer, Admin> repository;
	
	public AdminRepository() {
		this.repository = new HashMap<>();
		
		this.salvar(new Admin("ADM", "Rua da praia", "101010", "101010"));
	}
	
	public void salvar(Admin admin) {
		this.repository.put(admin.getId(), admin);
	}
	
	public void remover(Integer id) {
		this.repository.remove(id);
	}
	
	public List<Admin> buscarTodos(){
		return this.repository.values().stream().collect(Collectors.toList());
	}
	
	public Admin buscarPorId(Integer id) {
		return this.repository.get(id);
	}
	
}
