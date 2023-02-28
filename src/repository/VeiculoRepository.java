package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Veiculo;
import model.Veiculo.Combustivel;
import model.Veiculo.Segmento;

public class VeiculoRepository {

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
	
	public List<Veiculo> buscarTodos(){
		return this.repository.values().stream().collect(Collectors.toList());
	}
	
	public Veiculo buscarPorId(int id) {
		return this.repository.get(id);
	}
}
