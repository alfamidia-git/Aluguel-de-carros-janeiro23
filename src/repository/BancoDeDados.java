package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {

	private static Connection connection;
	private static final String URL = "jdbc:mysql://localhost:3306/aluguel_veiculos_jan23";
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	
	
	public static Connection obterConexao() {
		
		try {
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void fecharConexao() {
		try {
			
			if(connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String statusConexao() {
		try {
			return connection != null && !connection.isClosed() ? "Está aberta" : "Está fechada";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Erro ao verificar";
	}
}
