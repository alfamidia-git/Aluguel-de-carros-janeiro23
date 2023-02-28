package model;

import java.time.LocalDate;

import utils.Contador;
import utils.UtilsService;

public class Venda {

	private int id;
	private Vendedor vendedor;
	private Veiculo veiculo;
	private Cliente cliente;
	private LocalDate data;
	
	public Venda(Vendedor vendedor, Veiculo veiculo, Cliente cliente){
		this.id = Contador.proximoId();
		this.vendedor = vendedor;
		this.veiculo = veiculo;
		this.cliente = cliente;
		data = LocalDate.now();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String toString() {
		return "Vendedor: " + this.vendedor.getNome() + " - Cliente: " + this.cliente.getNome() 
		+ " - Veiculo: " + this.veiculo.getModelo() + ", " + this.veiculo.getMarca() + " " + this.veiculo.getPlaca() 
		+ " - Data: " + UtilsService.normalizaData(this.data);
	}
	
}
