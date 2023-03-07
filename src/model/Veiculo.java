package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import utils.Contador;

public class Veiculo {
	
	public enum Segmento{
		CARRO,
		MOTO,
		CAMINHAO
	}
	
	public enum Combustivel{
		GASOLINA,
		ALCOOL,
		HIBRIDO,
		ELETRICO
	}
	
	public enum Status{
		LIVRE,
		ALUGADO
	}
	
	private int id;
	private String placa;
	private String cor;
	private String marca;
	private String modelo;
	private int ano;
	private Segmento segmento;
	private Combustivel combustivel;
	private Status status;
	private LocalDate dataEntrega;
	private double valorDiario;
	
	public Veiculo(String placa, String cor, String marca, String modelo, int ano, Segmento segmento, 
					Combustivel combustivel, double valorDiario) {
		this.id = Contador.proximoId();
		this.placa = placa;
		this.marca = marca;
		this.cor = cor;
		this.modelo = modelo;
		this.segmento = segmento;
		this.ano = ano;
		this.combustivel = combustivel;
		this.valorDiario = valorDiario;
		this.status = Status.LIVRE;
	}
	
	public Veiculo(int id, String placa, String cor, String marca, String modelo, int ano, Segmento segmento, 
			Combustivel combustivel, double valorDiario, Status status) {
		
		this(placa, cor, marca, modelo, ano, segmento, combustivel, valorDiario);
		this.id = id;
		this.status = status;
	}
	
	public int getId() {
		return this.id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Segmento getSegmento() {
		return segmento;
	}
	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}
	public Combustivel getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
	
	public double getValorDiario() {
		return valorDiario;
	}
	public void setValorDiario(double valorDiario) {
		this.valorDiario = valorDiario;
	}

	@Override
	public String toString() {
		return id + " - " + marca + " - " + modelo + " - "+ placa + " - " + cor + " - " 
				+ " - " + combustivel + " - diaria: R$" + valorDiario 
				+ (this.dataEntrega != null ? " Data entrega: " + this.dataEntrega.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "");
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	
}
