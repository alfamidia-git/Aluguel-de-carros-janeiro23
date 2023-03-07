package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Veiculo;
import model.Veiculo.Combustivel;
import model.Veiculo.Segmento;
import model.Veiculo.Status;

public class VeiculoRepository {

	private Connection connection;
	private Map<Integer, Veiculo> repository;
	
	public VeiculoRepository() {
		repository = new HashMap<>();
		this.salvar(new Veiculo("IVX1234", "Preto", "Honda", "Civic", 2018, Segmento.CARRO, Combustivel.GASOLINA, 200));
		this.salvar(new Veiculo("YIX4534", "Branco", "Chevrolet", "Onix", 2020, Segmento.CARRO, Combustivel.GASOLINA, 200));
		this.salvar(new Veiculo("PYS3449", "Vermelho", "Audi", "A3", 2015, Segmento.CARRO, Combustivel.GASOLINA, 175));
		this.salvar(new Veiculo("IVX9876", "Vermelho", "Honda", "CG", 2018, Segmento.MOTO, Combustivel.GASOLINA, 120));
	}
	
	public void salvar(Veiculo veiculo) {
		this.repository.put(veiculo.getId(), veiculo);
	}
	
	public void deletar(int id) {
		this.repository.remove(id);
	}
	
	public List<Veiculo> buscarTodos(String condicao){
		this.connection = BancoDeDados.obterConexao();
		
		List<Veiculo> listaDeVeiculos = new ArrayList<>();
		
		String query = "SELECT * FROM `veiculo` ".concat(condicao == null ? "" : condicao);
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");;
				String cor = rs.getString("cor");
				Double valorDiario = rs.getDouble("valor_diario");
				int ano = rs.getInt("ano");
				Status status = Status.valueOf(rs.getString("status"));
				Segmento segmento = Segmento.valueOf(rs.getString("segmento"));
				Combustivel combustivel = Combustivel.valueOf(rs.getString("combustivel"));
				
				listaDeVeiculos.add( new Veiculo(id, placa, cor, marca, modelo, ano, segmento, 
														combustivel, valorDiario, status));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaDeVeiculos;
		
	}
	
	
	public Veiculo buscarPorId(int id) {
		
		this.connection = BancoDeDados.obterConexao();

		
		String query = "SELECT * FROM `veiculo` WHERE id = " + id;
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");;
				String cor = rs.getString("cor");
				Double valorDiario = rs.getDouble("valor_diario");
				int ano = rs.getInt("ano");
				Status status = Status.valueOf(rs.getString("status"));
				Segmento segmento = Segmento.valueOf(rs.getString("segmento"));
				Combustivel combustivel = Combustivel.valueOf(rs.getString("combustivel"));
				
				return new Veiculo(id, placa, cor, marca, modelo, ano, segmento, 
														combustivel, valorDiario, status);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
