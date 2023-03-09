package model;

public class Vendedor extends Pessoa{

	public static final double BONIFICACAO = 0.05;
	
	private double salario;
	private double comissao;
	private int vendas;
	
	public Vendedor(int id, String nome, String endereco, String cpf, String senha, double salario,
																double comissao, int vendas) {
		super(id, nome, endereco, cpf, senha);
		this.salario = salario;
		this.comissao = comissao;
		this.vendas = vendas;
	}
	
	public Vendedor(String nome, String endereco, String cpf, String senha, double salario) {
		super(nome, endereco, cpf, senha);
		this.salario = salario;
	}
	
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public double getComissao() {
		return comissao;
	}
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	public int getVendas() {
		return vendas;
	}
	public void addVendas() {
		this.vendas++;
	}
	
	public String toString() {
		return super.getId() + " - " + super.getNome();
	}
	
	
}
