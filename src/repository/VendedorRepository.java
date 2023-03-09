package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Vendedor;

public class VendedorRepository {
	
	Map<Integer, Vendedor> repository;
	private Connection connection;
	
	public VendedorRepository() {
		this.repository = new HashMap<>();
	}
	
	public void salvar(Vendedor vendedor) {
		Vendedor vendedorBD = this.buscarPorId(vendedor.getId());
		
		
		this.connection = BancoDeDados.obterConexao();
		
		String query = null;
		if(vendedorBD == null) {
			query = "INSERT INTO vendedor (nome, endereco, cpf, senha, salario, comissao, vendas) values (?, ?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				ps.setString(1, vendedor.getNome());
				ps.setString(2, vendedor.getEndereco());
				ps.setString(3, vendedor.getCpf());
				ps.setString(4, vendedor.getSenha());
				ps.setDouble(5, vendedor.getSalario());
				ps.setDouble(6, vendedor.getComissao());
				ps.setInt(7, vendedor.getVendas());
				
				if(ps.executeUpdate() == 1) {
					System.out.println("Vendedor salvo com sucesso!");
				}else {
					System.out.println("Erro ao salvar o vendedor");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			query = "UPDATE vendedor SET nome = ?, endereco = ?, cpf = ?, senha = "
					+ "?, salario = ?, comissao = ?, vendas = ? WHERE id = " + vendedor.getId();
			
			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				ps.setString(1, vendedor.getNome() == null ? vendedorBD.getNome() : vendedor.getNome());
				ps.setString(2, vendedor.getEndereco() == null ? vendedorBD.getEndereco() : vendedor.getEndereco());
				ps.setString(3, vendedor.getCpf() == null ? vendedorBD.getCpf() : vendedor.getCpf());
				ps.setString(4, vendedor.getSenha() == null ? vendedorBD.getSenha() : vendedor.getSenha());
				ps.setDouble(5, vendedor.getSalario());
				ps.setDouble(6, vendedor.getComissao());
				ps.setInt(7, vendedor.getVendas());
				
				if(ps.executeUpdate() == 1) {
					System.out.println("Vendedor atualizado com sucesso!");
				}else {
					System.out.println("Erro ao atualizar o vendedor");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void remover(Integer id) {
		this.repository.remove(id);
	}
	
	public List<Vendedor> buscarTodos(){
			this.connection = BancoDeDados.obterConexao();
			String query = "select * from vendedor";
			
			List<Vendedor> listaDeVendedores = new ArrayList<>();
			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String senha = rs.getString("senha");
					String endereco = rs.getString("endereco");
					String cpf = rs.getString("cpf");
					double salario = rs.getDouble("salario");
					double comissao = rs.getDouble("comissao");
					int vendas = rs.getInt("vendas");
					
					listaDeVendedores.add(new Vendedor(id, nome, endereco, cpf, null, salario, comissao, vendas));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				BancoDeDados.fecharConexao();
			}
			
			return listaDeVendedores;
			
	}
	
	public Vendedor buscarPorId(Integer id) {
		this.connection = BancoDeDados.obterConexao();
		String query = "select * from vendedor where id = " + id;
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String endereco = rs.getString("endereco");
				String cpf = rs.getString("cpf");
				double salario = rs.getDouble("salario");
				double comissao = rs.getDouble("comissao");
				int vendas = rs.getInt("vendas");
				
				return new Vendedor(id, nome, endereco, cpf, senha, salario, comissao, vendas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoDeDados.fecharConexao();
		}
		
		return null;
	}
}
