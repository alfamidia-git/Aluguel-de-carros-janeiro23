package model;

public class Admin extends Pessoa{

	public Admin(String nome, String endereco, String cpf, String senha) {
		super(nome, endereco, cpf, senha);
	}

	public Admin(int id, String nome, String endereco, String cpf, String senha) {
		super(id, nome, endereco, cpf, senha);		
	}

}
