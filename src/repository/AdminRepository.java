package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Admin;
import model.Cliente;

public class AdminRepository {

Map<Integer, Admin> repository;
	
	private Connection connection;
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
	
	public Admin buscarPorCpf(String cpf) {
//		return this.buscarTodos().stream().filter(cliente -> cliente.getCpf().equals(cpf)).findFirst().orElse(null);
		this.connection = BancoDeDados.obterConexao();

		String query = "SELECT * FROM admin where cpf = " + cpf;

		try {
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String endereco = rs.getString("endereco");

				return new Admin(id, nome, endereco, cpf, senha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoDeDados.fecharConexao();
		}

		return null;

	}
	
}
