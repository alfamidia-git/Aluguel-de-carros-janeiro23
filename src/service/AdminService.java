package service;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Admin;
import repository.AdminRepository;

public class AdminService {

	private AdminRepository repository;
	private Scanner sc;
	
	public AdminService(Scanner sc) {
		this.repository = new AdminRepository();
		this.sc = sc;
	}

	public Admin procurarAdmin(String cpf) {
		List<Admin> todosAdmins = this.repository.buscarTodos();
		
		return todosAdmins.stream().filter(ad -> ad.getCpf().equals(cpf)).findFirst().orElse(null);
	}
	
	
}
