package view;

import java.io.IOException;
import java.util.Scanner;
import controllers.CaminhaoDAO;
import controllers.CarroDAO;
import controllers.ClienteDAO;
import controllers.ContaDAO;
import controllers.MotoDAO;
import controllers.OnibusDAO;
import controllers.PatioDAO;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) throws IOException, InterruptedException {

		ContaDAO f2 = new ContaDAO();
		ClienteDAO f3 = new ClienteDAO();
		PatioDAO f4 = new PatioDAO();
		CarroDAO fichaCarro = new CarroDAO();
		MotoDAO fichaMoto = new MotoDAO();
		CaminhaoDAO fichaCaminhao = new CaminhaoDAO();
		OnibusDAO fichaOnibus = new OnibusDAO();

		sc = new Scanner(System.in);
		String str;
		int op = 0, op2 = 0, op3 = 0, op4 = 0, op5 = 0, op6 = 0, op7 = 0, op8 = 0, op9 = 0, op10 = 0;
		do {
			System.out.println("==========================");
			System.out.println("===   MENU PRINCIPAL   ===");
			System.out.println("==========================");
			System.out.println("1 - CONTA");
			System.out.println("2 - CLIENTE");
			System.out.println("3 - PÁTIO");
			System.out.println("4 - VEÍCULO");
			System.out.println("5 - SAIR");
			System.out.print("OPÇÃO: ");
			str = sc.nextLine();

			try {
				op = Integer.parseInt(str);
				if (op <= 0 || op > 5) {
					System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				op = 0;

			}

			switch (op) {

			case 1:
				do {
					System.out.println("\n--==[ CONTA ]==--\n");
					System.out.println("1 - CADASTRAR CONTA");
					System.out.println("2 - INCLUSÃO DE DIÁRIA");
					System.out.println("3 - EXCLUSÃO DE DIÁRIA");
					System.out.println("4 - TOTAL A PAGAR");
					System.out.println("5 - RELATÓRIO DE CONTAS");
					System.out.println("6 - VOLTAR AO MENU PRINCIPAL");
					System.out.print("OPÇÃO: ");
					str = sc.nextLine();
					System.out.print("\n");

					try {
						op2 = Integer.parseInt(str);
						if (op2 <= 0 || op2 > 6) {
							System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
						op2 = 0;
					}

					switch (op2) {
					case 1:
						f2.cadastrarConta();
						break;

					case 2:
						f2.incrementaDiaria();
						break;

					case 3:
						f2.decrementaDiaria();
						break;

					case 4:
						f2.totalPagar();
						break;

					case 5:
						f2.relatorio();
					case 6:
						break;

					}

				} while (op2 != 6);
				break;

			case 2:
				do {
					System.out.println("\n--==[ CLIENTE ]==--\n");
					System.out.println("1 - CADASTRO");
					System.out.println("2 - ALTERAÇÃO");
					System.out.println("3 - EXCLUSÃO");
					System.out.println("4 - CONSULTA");
					System.out.println("5 - RELATÓRIO");
					System.out.println("6 - VOLTAR AO MENU PRINCIPAL");
					System.out.print("OPÇÃO: ");
					str = sc.nextLine();

					try {
						op3 = Integer.parseInt(str);
						if (op3 <= 0 || op3 > 6) {
							System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
						op3 = 0;

					}
					switch (op3) {
					case 1:
						f3.addCliente();
						break;

					case 2:
						f3.alteraCliente();
						break;

					case 3:
						f3.removeCliente();
						break;

					case 4:
						f3.consultaCliente();
						break;

					case 5:
						f3.relatorioCliente();
						break;

					case 6:

						break;

					}

				} while (op3 != 6);
				break;

			case 3:
				do {
					System.out.println("\n--==[ PÁTIO ]==--\n");
					System.out.println("1 - CADASTRO");
					System.out.println("2 - ALTERAÇÃO");
					System.out.println("3 - EXCLUSÃO");
					System.out.println("4 - CONSULTA");
					System.out.println("5 - RELATÓRIO");
					System.out.println("6 - VOLTAR AO MENU PRINCIPAL");
					System.out.print("OPÇÃO: ");
					str = sc.nextLine();

					try {
						op4 = Integer.parseInt(str);
						if (op4 <= 0 || op4 > 6) {
							System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
						op4 = 0;
					}
					switch (op4) {
					case 1:
						f4.addPatio();
						break;

					case 2:
						f4.alteraPatio();
						break;

					case 3:
						f4.removePatio();
						break;

					case 4:
						f4.consultaPatio();
						break;

					case 5:
						f4.relatorioPatio();
						break;

					case 6:
						break;

					}

				} while (op4 != 6);
				break;

			case 4:
				do {
					System.out.println("\n--==[ VEÍCULO ]==--\n");
					System.out.println("1 - CADASTRO");
					System.out.println("2 - ALTERAÇÃO");
					System.out.println("3 - EXCLUSÃO");
					System.out.println("4 - CONSULTA");
					System.out.println("5 - RELATÓRIO");
					System.out.println("6 - VOLTAR AO MENU PRINCIPAL");
					System.out.print("OPÇÃO: ");
					str = sc.nextLine();

					try {
						op5 = Integer.parseInt(str);
						if (op5 <= 0 || op5 > 6) {
							System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
						op5 = 0;

					}
					switch (op5) {
					case 1:
						do {
							System.out.println("\n--==[ VEÍCULO ]==--\n");
							System.out.println("1 - CARRO");
							System.out.println("2 - MOTO");
							System.out.println("3 - CAMINHÃO");
							System.out.println("4 - ÔNIBUS");
							System.out.println("5 - VOLTAR AO MENU ANTERIOR");
							System.out.print("OPÇÃO: ");
							str = sc.nextLine();
							try {
								op6 = Integer.parseInt(str);
								if (op6 <= 0 || op6 > 5) {
									System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
								op6 = 0;

							}

							switch (op6) {

							case 1:

								fichaCarro.adicionaCarro();
								break;

							case 2:

								fichaMoto.adicionaMoto();
								break;

							case 3:

								fichaCaminhao.adicionaCaminhao();
								break;

							case 4:
								fichaOnibus.adicionaOnibus();
								break;
							case 5:
								break;
							}
						} while (op6 != 5);

						break;

					case 2:
						do {
							System.out.println("\n--==[ ALTERAÇÃO DE VEÍCULO ]==--\n");
							System.out.println("1 - CARRO");
							System.out.println("2 - MOTO");
							System.out.println("3 - CAMINHÃO");
							System.out.println("4 - ÔNIBUS");
							System.out.println("5 - VOLTAR AO MENU ANTERIOR");
							System.out.print("OPÇÃO: ");
							str = sc.nextLine();
							try {
								op7 = Integer.parseInt(str);
								if (op7 <= 0 || op7 > 5) {
									System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
								op7 = 0;

							}

							switch (op7) {

							case 1:

								fichaCarro.alteraCarro();
								break;

							case 2:

								fichaMoto.alteraMoto();
								break;

							case 3:

								fichaCaminhao.alteraCaminhao();
								break;

							case 4:

								fichaOnibus.alteraOnibus();
								break;

							case 5:
								break;

							}
						} while (op7 != 5);

						break;

					case 3:
						do {
							System.out.println("\n--==[ EXCLUSÃO DE VEÍCULO ]==--\n");
							System.out.println("1 - CARRO");
							System.out.println("2 - MOTO");
							System.out.println("3 - CAMINHÃO");
							System.out.println("4 - ÔNIBUS");
							System.out.println("5 - VOLTAR AO MENU ANTERIOR");
							System.out.print("OPÇÃO: ");
							str = sc.nextLine();
							try {
								op8 = Integer.parseInt(str);
								if (op8 <= 0 || op8 > 5) {
									System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
								op8 = 0;

							}

							switch (op8) {

							case 1:

								fichaCarro.removeCarro();
								break;

							case 2:

								fichaMoto.removeMoto();
								break;

							case 3:

								fichaCaminhao.removeCaminhao();
								break;

							case 4:

								fichaOnibus.removeOnibus();
								break;

							case 5:

								break;

							}
						} while (op8 != 5);

						break;

					case 4:
						do {
							System.out.println("\n--==[ CONSULTA DE VEÍCULO ]==--\n");
							System.out.println("1 - CARRO");
							System.out.println("2 - MOTO");
							System.out.println("3 - CAMINHÃO");
							System.out.println("4 - ÔNIBUS");
							System.out.println("5 - VOLTAR AO MENU ANTERIOR");
							System.out.print("OPÇÃO: ");
							str = sc.nextLine();
							try {
								op9 = Integer.parseInt(str);
								if (op9 <= 0 || op9 > 5) {
									System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
								op9 = 0;

							}

							switch (op9) {

							case 1:
								fichaCarro.consultaCarro();
								break;

							case 2:
								fichaMoto.consultaMoto();
								break;

							case 3:
								fichaCaminhao.consultaCaminhao();
								break;

							case 4:
								fichaOnibus.consultaOnibus();
								break;

							case 5:

								break;

							}
						} while (op9 != 5);

						break;

					case 5:
						do {
							System.out.println("\n--==[ RELATÓRIO DE VEÍCULO ]==--\n");
							System.out.println("1 - CARRO");
							System.out.println("2 - MOTO");
							System.out.println("3 - CAMINHÃO");
							System.out.println("4 - ÔNIBUS");
							System.out.println("5 - VOLTAR AO MENU ANTERIOR");
							System.out.print("OPÇÃO: ");
							str = sc.nextLine();
							try {
								op10 = Integer.parseInt(str);
								if (op10 <= 0 || op10 > 5) {
									System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
								op10 = 0;
							}

							switch (op10) {

							case 1:
								fichaCarro.relatorioCarro();
								break;

							case 2:
								fichaMoto.relatorioMoto();
								break;

							case 3:
								fichaCaminhao.relatorioCaminhao();
								break;

							case 4:
								fichaOnibus.relatorioOnibus();
								break;

							case 5:

								break;

							}
						} while (op10 != 5);

						break;

					case 6:

						break;

					}

				} while (op5 != 6);
				break;

			case 5:
				System.out.println("\nSISTEMA FINALIZADO.");
				break;
			}

		} while (op != 5);

	}

}