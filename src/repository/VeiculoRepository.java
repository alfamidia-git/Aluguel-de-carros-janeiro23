package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.Date;
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
	}

	public void salvar(Veiculo veiculo) {
		Veiculo veiculoBD = this.buscarPorId(veiculo.getId());
		String query = null;
		this.connection = BancoDeDados.obterConexao();
		if (veiculoBD == null) {
			query = "INSERT INTO veiculo (placa, cor, marca, modelo, ano, segmento, combustivel, "
					+ "status, data_entrega, valor_diario) values(?, ?, ? , ?, ?, ?, ?, ?, ?, ?)";
		} else {
			query = "UPDATE veiculo SET placa = ?, cor = ?, marca = ?, modelo = ?, ano = ?, segmento = ?, "
					+ "combustivel =?, status = ?, data_entrega = ?, valor_diario = ? WHERE ID = " + veiculo.getId();
		}

		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, veiculo.getPlaca());
			ps.setString(2, veiculo.getCor());
			ps.setString(3, veiculo.getMarca());
			ps.setString(4, veiculo.getModelo());
			ps.setInt(5, veiculo.getAno());
			ps.setString(6, veiculo.getSegmento().toString());
			ps.setString(7, veiculo.getCombustivel().toString());
			ps.setString(8, veiculo.getStatus().toString());
			ps.setDate(9, veiculo.getDataEntrega() == null ? null : Date.valueOf(veiculo.getDataEntrega()));
			ps.setDouble(10, veiculo.getValorDiario());

			if (ps.executeUpdate() == 1) {
				System.out.println("Veiculo salvo com sucesso!");
			} else {
				System.out.println("Erro ao salvar veiculo!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletar(int id) {
		this.repository.remove(id);
	}

	public List<Veiculo> buscarTodos(String condicao) {
		this.connection = BancoDeDados.obterConexao();

		List<Veiculo> listaDeVeiculos = new ArrayList<>();

		String query = "SELECT * FROM `veiculo` ".concat(condicao == null ? "" : condicao);

		try {
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				;
				String cor = rs.getString("cor");
				Double valorDiario = rs.getDouble("valor_diario");
				int ano = rs.getInt("ano");
				Status status = Status.valueOf(rs.getString("status"));
				Segmento segmento = Segmento.valueOf(rs.getString("segmento"));
				Combustivel combustivel = Combustivel.valueOf(rs.getString("combustivel"));

				listaDeVeiculos.add(
						new Veiculo(id, placa, cor, marca, modelo, ano, segmento, combustivel, valorDiario, status));

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

			while (rs.next()) {
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				;
				String cor = rs.getString("cor");
				Double valorDiario = rs.getDouble("valor_diario");
				int ano = rs.getInt("ano");
				Status status = Status.valueOf(rs.getString("status"));
				Segmento segmento = Segmento.valueOf(rs.getString("segmento"));
				Combustivel combustivel = Combustivel.valueOf(rs.getString("combustivel"));

				return new Veiculo(id, placa, cor, marca, modelo, ano, segmento, combustivel, valorDiario, status);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BancoDeDados.fecharConexao();
		}

		return null;
	}

	public List<Veiculo> buscarVeiculosPeloClienteId(int idCliente) {
		List<Veiculo> listaDeVeiculos = new ArrayList<>();

		String query = "SELECT veiculo.* FROM `veiculo`" + "INNER JOIN venda on venda.id_veiculo = veiculo.id "
				+ "WHERE status = 'ALUGADO' AND venda.entregue = false AND id_cliente = " + idCliente;

		this.connection = BancoDeDados.obterConexao();

		try {
			PreparedStatement ps = this.connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				;
				String cor = rs.getString("cor");
				Double valorDiario = rs.getDouble("valor_diario");
				int ano = rs.getInt("ano");
				Status status = Status.valueOf(rs.getString("status"));
				Segmento segmento = Segmento.valueOf(rs.getString("segmento"));
				Combustivel combustivel = Combustivel.valueOf(rs.getString("combustivel"));

				listaDeVeiculos.add(
						new Veiculo(id, placa, cor, marca, modelo, ano, segmento, combustivel, valorDiario, status));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDeVeiculos;
	}
}
