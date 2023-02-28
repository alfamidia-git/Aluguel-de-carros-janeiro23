package utils;

public class Menu {

	public static void bemVindo() {
		System.out.println("BEM VINDO AO SISTEMA DE ALUGUEL DE VEICULOS");
		System.out.println("1 - CLIENTE");
		System.out.println("2 - VENDEDOR");
		System.out.println("3 - ADMINISTRADOR");
		System.out.println("0 - PARA SAIR E ENCERRAR");

	}
	
	public static void menuCliente() {
		System.out.println("1 - ALUGAR UM VEÍCULO");
		System.out.println("2 - DEVOLVER UM VEÍCULO");
		System.out.println("3 - VER VEÍCULOS ALUGADOS");
	}
	
	public static void menuVendedor() {
		System.out.println("1 - VER TOTAL DE VENDAS E SEU SALÁRIO COM COMISSÃO");
		System.out.println("2 - VER VEÍCULOS QUE VOCÊ ALUGOU E PARA QUAL CLIENTE");
	}
	
	public static void menuAdmin() {
		System.out.println("1 - CRIAR UM NOVO VEÍCULO ");
		System.out.println("2 - REMOVER UM VEÍCULO");
		System.out.println("3 - CRIAR UM NOVO VENDEDOR");
		System.out.println("4 - REMOVER UM VENDEDOR");
	}
}
