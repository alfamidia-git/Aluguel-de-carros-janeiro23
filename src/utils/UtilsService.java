package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.Pessoa;

public class UtilsService {

	
	public static boolean confereSenha(Pessoa pessoa, String senha) {
		return pessoa.getSenha().equals(senha);
	}
	
	public static String normalizaData(LocalDate data) {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
