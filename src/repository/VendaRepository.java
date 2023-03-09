package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Venda;

public class VendaRepository {

	Map<Integer, Venda> repository;

	private Connection connection;

	public VendaRepository() {
		this.repository = new HashMap<>();
	}

	public void salvar(Venda venda) {
		this.connection = BancoDeDados.obterConexao();
		if (venda.getId() != null) {
			String query = "UPDATE venda SET entregue = " + venda.getEntregue() + " where id = " + venda.getId();
			
			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				if(ps.executeUpdate() == 1) {
					System.out.println("Veiculo devolvido com sucesso!");
				}else {
					System.out.println("Erro ao devolver o veículo");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {

			String query = "INSERT INTO venda (id_cliente, id_vendedor, id_veiculo, data, entregue) values (?, ?, ?, ?, ?)";

			try {
				PreparedStatement ps = this.connection.prepareStatement(query);
				ps.setInt(1, venda.getCliente().getId());
				ps.setInt(2, venda.getVendedor().getId());
				ps.setInt(3, venda.getVeiculo().getId());
				ps.setDate(4, Date.valueOf(venda.getData()));
				ps.setBoolean(5, false);

				if (ps.executeUpdate() == 1) {
					System.out.println("Venda salva com sucesso!");
				} else {
					System.out.println("Erro ao salvar venda!");
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

	public List<Venda> buscarTodos() {
		return this.repository.values().stream().collect(Collectors.toList());
	}

	public Venda buscarPorId(Integer id) {
		return this.repository.get(id);
	}

	public Venda buscarPeloClienteEVeiculo(int clienteId, int veiculoId) {
		String query = "SELECT * FROM venda where id_cliente = ? AND id_veiculo = ? AND entregue = false";
		this.connection = BancoDeDados.obterConexao();

		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, clienteId);
			ps.setInt(2, veiculoId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				boolean entregue = rs.getBoolean("entregue");

				return new Venda(id, entregue);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
