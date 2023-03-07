package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cliente;

public class ClienteRepository {

	private Map<Integer, Cliente> repository;
	private Connection connection;
	
	public ClienteRepository() {
		this.repository = new HashMap<>();
		Cliente cliente = new Cliente("Marlon", "Rua 1", "12345", "12345");
		this.repository.put(cliente.getId(), cliente);
	}
	
	public void salvar(Cliente cliente) {
//		this.repository.put(cliente.getId(), cliente);
		
		this.connection = BancoDeDados.obterConexao();
		
		String query = "INSERT INTO cliente (nome, endereco, cpf, senha) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEndereco());
			ps.setString(3, cliente.getCpf());
			ps.setString(4, cliente.getSenha());
			
			int sucesso = ps.executeUpdate();
			
			if(sucesso == 1) {
				System.out.println("Salvo com sucesso no banco de dados!");
			}else {
				System.out.println("Erro ao salvar no banco de dados!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoDeDados.fecharConexao();
		}
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
//		return this.buscarTodos().stream().filter(cliente -> cliente.getCpf().equals(cpf)).findFirst().orElse(null);
		this.connection = BancoDeDados.obterConexao();
		
		String query = "SELECT * FROM cliente where cpf = " + cpf;
		
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String endereco = rs.getString("endereco");
				
				return new Cliente(id, nome, endereco, cpf, senha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BancoDeDados.fecharConexao();
		}
		
		return null;
		
		
	}
}
