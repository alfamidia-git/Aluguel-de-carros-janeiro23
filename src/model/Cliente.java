package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
	
		double valorDebito;
		List<Veiculo> veiculosAlugados;
	
		public Cliente(int id, String nome, String endereco, String cpf, String senha, Double valorDebito) {
			super(id, nome, endereco, cpf, senha);
			this.valorDebito = valorDebito;
			veiculosAlugados = new ArrayList<>();
		}
		
		public Cliente(String nome, String endereco, String cpf, String senha) {
		super(nome, endereco, cpf, senha);
		
		this.veiculosAlugados = new ArrayList<>();
		}
		
		public double getValorDebito() {
			return valorDebito;
		}
		public void setValorDebito(double valorDebito) {
			this.valorDebito = valorDebito;
		}
		public List<Veiculo> getVeiculosAlugados() {
			return veiculosAlugados;
		}
		public void setVeiculosAlugados(List<Veiculo> veiculosAlugados) {
			this.veiculosAlugados = veiculosAlugados;
		}
		
		
}
