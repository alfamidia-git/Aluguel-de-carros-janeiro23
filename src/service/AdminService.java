package service;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
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
		Admin admin = this.repository.buscarPorCpf(cpf);
		
		if(admin == null) {
			throw new SistemaException("Admin não localizado");
		}
		return admin;
	}
	
	
}
