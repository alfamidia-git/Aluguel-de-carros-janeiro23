package model;

import utils.Contador;

public abstract class Pessoa {

	private int id;
	private String nome;
	private String endereco;
	private String cpf;
	private String senha;
	
	
	public Pessoa(String nome, String endereco, String cpf, String senha) {
		this.id = Contador.proximoId();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.senha = senha;
	}

	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
