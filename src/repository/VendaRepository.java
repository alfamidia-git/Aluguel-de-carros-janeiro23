package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cliente;
import model.Veiculo;
import model.Venda;
import model.Vendedor;

public class VendaRepository {

	Map<Integer, Venda> repository;

	private Connection connection;

	ClienteRepository clienteRepository;
	VendedorRepository vendedorRepository;
	VeiculoRepository veiculoRepository;
	
	public VendaRepository() {
		this.repository = new HashMap<>();
		
		clienteRepository = new ClienteRepository();
		vendedorRepository = new VendedorRepository();
		veiculoRepository = new VeiculoRepository();
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
	
	public List<Venda> buscarVendasPorVendedor(int vendedorId){
		List<Venda> listaDeVendas = new ArrayList<>();
		
		this.connection = BancoDeDados.obterConexao();
		
		String query = "SELECT * FROM venda WHERE id_vendedor = " + vendedorId;
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int clienteId = rs.getInt("id_cliente");
				int veiculoId = rs.getInt("id_veiculo");
				LocalDate data = rs.getDate("data").toLocalDate();
				
				Cliente cliente = this.clienteRepository.buscarPorId(clienteId, false);
				Veiculo veiculo = this.veiculoRepository.buscarPorId(veiculoId, false);
				Vendedor vendedor = this.vendedorRepository.buscarPorId(vendedorId, false);
				
				listaDeVendas.add(new Venda(id, cliente, veiculo, vendedor, data));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BancoDeDados.fecharConexao();
		}
		
		return listaDeVendas;
	}
}
