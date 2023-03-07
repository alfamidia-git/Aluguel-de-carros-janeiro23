import java.util.InputMismatchException;
import java.util.Scanner;

import exception.SistemaException;
import model.Admin;
import model.Cliente;
import model.Veiculo;
import model.Vendedor;
import repository.BancoDeDados;
import service.AdminService;
import service.ClienteService;
import service.VeiculoService;
import service.VendedorService;
import utils.Menu;
import utils.UtilsService;

public class Principal {

	public static void main(String args[]) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		boolean continuar = true;

		VeiculoService veiculoService = new VeiculoService(sc);
		ClienteService clienteService = new ClienteService(sc);
		VendedorService vendedorService = new VendedorService(sc);
		AdminService adminService = new AdminService(sc);

		do {
			try {
				Menu.bemVindo();
				int opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					System.out.println("DIGITE SEU CPF: ");
					String cpf = sc.next();
					Cliente cliente = clienteService.procurarCliente(cpf);

					System.out.println("Bem vindo Sr " + cliente.getNome());
					boolean senhaIncorreta = true;
					do {
						System.out.println("Digite sua senha:");
						String senha = sc.next();
						senhaIncorreta = !UtilsService.confereSenha(cliente, senha);
					} while (senhaIncorreta);

					Menu.menuCliente();
					int opcaoCliente = sc.nextInt();

					if (opcaoCliente == 1) {
						System.out.println("Digite o número referente a sua escolha: ");
						veiculoService.mostrarTodosVeiculosLivres();
						int opcaoAluguel = sc.nextInt();

						Veiculo veiculo = veiculoService.buscarPorId(opcaoAluguel);

						System.out.println("Quantos dias você ficará com o veículo:");
						int diasAlugado = sc.nextInt();
						clienteService.alugarVeiculo(cliente, veiculo, diasAlugado);
						veiculoService.statusAlugado(veiculo, diasAlugado);

						System.out.println("Escolha qual vendedor lhe atendeu: ");
						vendedorService.mostrarTodosVendedores();
						int opcaoVendedor = sc.nextInt();
						vendedorService.adicionarVenda(opcaoVendedor, veiculo, diasAlugado, cliente);

					} else if (opcaoCliente == 2) {
						System.out.println("Seus veículos atuais: ");
						clienteService.mostrarVeiculosAlugados(cliente);

						int opcaoDevolve = sc.nextInt();

						Veiculo veiculo = veiculoService.buscarPorId(opcaoDevolve);

						clienteService.devolverVeiculo(cliente, veiculo);
						veiculoService.statusLivre(veiculo);

					} else if (opcaoCliente == 3) {
						clienteService.mostrarVeiculosEDebitos(cliente);
					} else {
						System.out.println("Opção incorreta");
					}

					break;
				case 2:

					System.out.println("DIGITE SEU CPF: ");
					cpf = sc.next();
					Vendedor vendedor = vendedorService.procurarVendedor(cpf);

					System.out.println("Bem vindo Sr " + vendedor.getNome());
					senhaIncorreta = true;
					do {
						System.out.println("Digite sua senha:");
						String senha = sc.next();
						senhaIncorreta = !UtilsService.confereSenha(vendedor, senha);
					} while (senhaIncorreta);

					Menu.menuVendedor();
					int opcaoVendedor = sc.nextInt();

					if (opcaoVendedor == 1) {
						vendedorService.mostrarDetalhesDoVendedor(vendedor);
					} else if (opcaoVendedor == 2) {
						vendedorService.mostrarVendasDoVendedor(vendedor);
					} else {
						System.out.println("OPÇÃO INVÁLIDA!");
					}

					break;
				case 3:

					System.out.println("DIGITE SEU CPF: ");
					cpf = sc.next();
					Admin admin = adminService.procurarAdmin(cpf);

					System.out.println("Bem vindo Sr " + admin.getNome());
					senhaIncorreta = true;
					do {
						System.out.println("Digite sua senha:");
						String senha = sc.next();
						senhaIncorreta = !UtilsService.confereSenha(admin, senha);
					} while (senhaIncorreta);
					Menu.menuAdmin();
					int opcaoAdmin = sc.nextInt();

					if (opcaoAdmin == 1) {
						veiculoService.criarVeiculo();
					} else if (opcaoAdmin == 2) {
						veiculoService.mostrarTodosVeiculos();
						int opcaoVeiculo = sc.nextInt();
						veiculoService.removerUmVeiculo(opcaoVeiculo);
					} else if (opcaoAdmin == 3) {
						vendedorService.criarNovoVendedor();
					} else if (opcaoAdmin == 4) {
						vendedorService.mostrarTodosVendedores();
						opcaoVendedor = sc.nextInt();

						vendedorService.removerUmVendedor(opcaoVendedor);
					}
					break;
				case 0:
					continuar = false;
					break;
				default:
					break;
				}
			} catch (SistemaException e) {
				System.out.println("ERRO NO SISTEMA: " + e.getMessage());
				continuar = true;
			}catch (InputMismatchException e){
				System.out.println("Operação não permitida. Tente novamente");
				sc.nextLine();
			}finally {
				System.out.println("----------------------------");
				System.out.println();
				Thread.sleep(3000);
			}
		} while (continuar);
	}
}
