package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import models.Cliente;
import models.Conta;
import models.Patio;
import models.Veiculo;

public class ContaDAO {
	private Conta conta;
	private Veiculo veiculo;
	private Cliente cliente;
	private Patio patio;
	Calendar cal;
	int anoAtual;
	Scanner sc;
	String sql;
	Connection con;
	PreparedStatement stm;

	public ContaDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
		cal = Calendar.getInstance();
		anoAtual = cal.get(Calendar.YEAR);

	}

	public void cadastrarConta() {
		int mes = 0, cont = -1, ano = 0, aux = 0;
		conta = new Conta();
		String str;
		System.out.println("--== [ CADASTRO DE CONTA ] ==--\n");
		if (listaCliente.isEmpty()) {
			System.out.println("\nN�O H� CLIENTES CADASTRADOS.\n");
			return;
		} else {
			// cliente = f2.retornaCliente(listaCliente);
			if (cliente == null) {
				return;
			} else {
				if (listaVeiculo.isEmpty()) {
					System.out.println("\nN�O H� VE�CULOS CADASTRADOS.\n");
					return;
				} else {
					// veiculo = f1.retornaVeiculo(listaVeiculo);
					if (veiculo == null) {
						return;
					} else {
						if (listaPatio.isEmpty()) {
							System.out.println("\nN�O H� P�TIOS CADASTRADOS.\n");
							return;
						} else {
							// patio = f3.retornaPatio(listaPatio);
							if (patio == null) {
								return;
							}
						}
					}

				}
			}

		}

		if (listaConta.isEmpty()) {
			conta.setCliente(cliente);
			conta.setVeiculo(veiculo);
			conta.setPatio(patio);
		} else {
			for (int i = 0; i < listaConta.size(); i++) {
				if (listaConta.get(i).getCliente().getCodigo() != cliente.getCodigo()
						&& listaConta.get(i).getVeiculo().getCodigo() == veiculo.getCodigo()) {
					cont++;
				}
			}
			if (cont >= 0) {
				System.out.println("\nVE�CULO INFORMADO PERTENCE A OUTRO CLIENTE.\n");
				veiculo = null;
			} else {
				conta.setCliente(cliente);
				conta.setVeiculo(veiculo);
				conta.setPatio(patio);
			}
		}

		if (conta.getCliente() != null && conta.getVeiculo() != null && conta.getPatio() != null) {
			int resp = 1;
			do {
				System.out.println("DIGITE O M�S: ");
				str = sc.nextLine();
				try {
					mes = Integer.parseInt(str);
					if (mes < 1 || mes > 12) {
						System.out.println("\nM�S INV�LIDO.");
						do {
							System.out.println("\nDESEJA CONTINUAR");
							System.out.println("1 - SIM");
							System.out.println("2 - N�O");
							System.out.print("OP��O: ");
							str = sc.nextLine();
							System.out.print("\n");
							try {
								resp = Integer.parseInt(str);
								if (resp < 1 || resp > 2) {
									System.out.println("\nOP��O INV�LIDA.\n");
									System.out.println("DIGITE OP��O V�LIDA.\n");
								} else if (resp == 2) {
									return;
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
							}
						} while (resp < 1 || resp > 2);

					}
				} catch (NumberFormatException e) {

					System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				}
			} while (mes < 1 || mes > 12 || resp != 1);

			do {
				System.out.println("DIGITE O ANO: ");
				str = sc.nextLine();
				try {
					ano = Integer.parseInt(str);
					if (ano < anoAtual || ano > anoAtual) {
						System.out.println("\nANO INV�LIDO.");
						System.out.println("O ANO DIGITADO DEVE SER O ANO ATUAL:" + anoAtual);
						do {
							System.out.println("DESEJA CONTINUAR");
							System.out.println("1 - SIM");
							System.out.println("2 - N�O");
							System.out.print("OP��O: ");
							str = sc.nextLine();
							System.out.print("\n");
							try {
								resp = Integer.parseInt(str);
								if (resp < 1 || resp > 2) {
									System.out.println("\nOP��O INV�LIDA.\n");
									System.out.println("DIGITE OP��O V�LIDA.\n");
								} else if (resp == 2) {
									return;
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
							}
						} while (resp < 1 || resp > 2);

					}
				} catch (NumberFormatException e) {

					System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				}
			} while (ano < anoAtual || ano > anoAtual || resp != 1);

			if (listaConta.isEmpty()) {
				index = 1;
			} else {

				while (index == 0 && aux < listaConta.size()) {
					if (listaConta.get(aux).getCliente().getCodigo() != cliente.getCodigo()
							&& listaConta.get(aux).getVeiculo().getCodigo() != veiculo.getCodigo()) {
						aux++;
					} else if (listaConta.get(aux).getCliente().getCodigo() == cliente.getCodigo()
							&& listaConta.get(aux).getVeiculo().getCodigo() == veiculo.getCodigo()
							&& listaConta.get(aux).getPatio().getCodigo() == patio.getCodigo()) {
						if (ano != listaConta.get(aux).getAno()) {
							aux++;
						} else {
							if (mes != listaConta.get(aux).getMes()) {
								aux++;
							} else {
								index = -1;
							}
						}
					} else if (listaConta.get(aux).getCliente().getCodigo() == cliente.getCodigo()
							&& listaConta.get(aux).getPatio().getCodigo() == patio.getCodigo()) {
						if (listaConta.get(aux).getVeiculo().getCodigo() != veiculo.getCodigo()) {
							aux++;
						} else {
							index = -1;
						}
					} else if (listaConta.get(aux).getCliente().getCodigo() == cliente.getCodigo()) {
						if (listaConta.get(aux).getPatio().getCodigo() != patio.getCodigo()) {
							aux++;
						} else {
							index = -1;
						}
					}
				}
			}

			if (index >= 0) {
				conta.getPatio().setLotacao(conta.getPatio().getLotacao() + 1);
				if (conta.getPatio().getLotacao() <= conta.getPatio().getCapacidadeDeVeiculos()) {
					conta.setMes(mes);
					conta.setAno(ano);
					listaConta.add(conta);
					System.out.println("\nCONTA CADASTRADA COM SUCESSO.\n");
				} else {
					System.out.println("\nCAPACIDADE M�XIMA P�TIO ATINGIDA.\n");
					conta.getPatio().setLotacao(conta.getPatio().getLotacao() - 1);
				}
			} else {
				System.out.println("\nDADOS J� CADASTRADOS EM OUTRA CONTA.\n");
			}
		} else {
			System.out.println("\nCONTA N�O CADASTRADA.\n");
		}
	}

	public void incrementaDiaria() {

		index = -1;
		String str;
		int mes = 0, ano = 0, aux = 0;
		System.out.println("--== [ ADICIONAR DI�RIA ] ==--\n");
		if (listaCliente.isEmpty()) {
			System.out.println("\nN�O H� CLIENTES CADASTRADOS.\n");
			return;
		} else {
			// cliente = f2.retornaCliente(listaCliente);
			if (cliente == null) {
				return;
			} else {
				if (listaVeiculo.isEmpty()) {
					System.out.println("\nN�O H� VE�CULOS CADASTRADOS.\n");
					return;
				} else {
					// veiculo = f1.retornaVeiculo(listaVeiculo);
					if (veiculo == null) {
						return;
					} else {
						if (listaPatio.isEmpty()) {
							System.out.println("\nN�O H� P�TIOS CADASTRADOS.\n");
							return;
						} else {
							// patio = f3.retornaPatio(listaPatio);
							if (patio == null) {
								return;
							}
						}
					}

				}
			}

		}

		int resp = 1;
		do {
			System.out.println("DIGITE O M�S: ");
			str = sc.nextLine();
			try {
				mes = Integer.parseInt(str);
				if (mes < 1 || mes > 12) {
					System.out.println("\nM�S INV�LIDO.");
					do {
						System.out.println("DESEJA CONTINUAR\n");
						System.out.println("1 - SIM");
						System.out.println("2 - N�O");
						System.out.print("OP��O: ");
						str = sc.nextLine();
						System.out.print("\n");
						try {
							resp = Integer.parseInt(str);
							if (resp < 1 || resp > 2) {
								System.out.println("\nOP��O INV�LIDA.\n");
								System.out.println("DIGITE OP��O V�LIDA.\n");
							} else if (resp == 2) {
								return;
							}
						} catch (NumberFormatException e) {

							System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						}
					} while (resp < 1 || resp > 2);

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			}
		} while (mes < 1 || mes > 12 || resp != 1);

		do {
			System.out.println("DIGITE O ANO: ");
			str = sc.nextLine();
			try {
				ano = Integer.parseInt(str);
				if (ano < 2017) {
					System.out.println("\nANO INV�LIDO.");
					System.out.println("O ANO DIGITADO DEVE SER O MAIOR OU IGUAL A 2017\n");
					do {
						System.out.println("\nDESEJA CONTINUAR");
						System.out.println("1 - SIM");
						System.out.println("2 - N�O");
						System.out.print("OP��O: ");
						str = sc.nextLine();
						System.out.print("\n");
						try {
							resp = Integer.parseInt(str);
							if (resp < 1 || resp > 2) {
								System.out.println("\nOP��O INV�LIDA.\n");
								System.out.println("DIGITE OP��O V�LIDA.\n");
							} else if (resp == 2) {
								return;
							}
						} catch (NumberFormatException e) {

							System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						}
					} while (resp < 1 || resp > 2);

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			}
		} while (ano < 2017 || resp != 1);

		while (aux < listaConta.size() && index == -1) {
			if (listaConta.get(aux).getCliente().getCodigo() == cliente.getCodigo()
					&& listaConta.get(aux).getVeiculo().getCodigo() == veiculo.getCodigo()
					&& listaConta.get(aux).getPatio().getCodigo() == patio.getCodigo()
					&& listaConta.get(aux).getMes() == mes && listaConta.get(aux).getAno() == ano) {
				index = listaConta.indexOf(listaConta.get(aux));
			} else {
				aux++;
			}
		}

		if (index >= 0) {
			listaConta.get(index).setDiarias(listaConta.get(index).getDiarias() + 1);
			System.out.println("\nDI�RIA ADICIONADA COM SUCESSO.\n");
		} else {
			System.out.println("\nN�O H� CONTA CADASTRADA COM ESTAS INFORMA��ES.");
			System.out.println("\nVERIFIQUE AS INFORMA��ES DE CADASTRO.");
		}
	}

	public void decrementaDiaria() {
		index = -1;
		int mes = 0, ano = 0, aux = 0;
		String str;
		System.out.println("--== [ EXCLUIR DI�RIA ] ==--\n");
		if (listaCliente.isEmpty()) {
			System.out.println("\nN�O H� CLIENTES CADASTRADOS.\n");
			return;
		} else {
			// cliente = f2.retornaCliente(listaCliente);
			if (cliente == null) {
				return;
			} else {
				if (listaVeiculo.isEmpty()) {
					System.out.println("\nN�O H� VE�CULOS CADASTRADOS.\n");
					return;
				} else {
					// veiculo = f1.retornaVeiculo(listaVeiculo);
					if (veiculo == null) {
						return;
					} else {
						if (listaPatio.isEmpty()) {
							System.out.println("\nN�O H� P�TIOS CADASTRADOS.\n");
							return;
						} else {
							// patio = f3.retornaPatio(listaPatio);
							if (patio == null) {
								return;
							}
						}
					}

				}
			}

		}

		int resp = 1;
		do {
			System.out.println("DIGITE O M�S: ");
			str = sc.nextLine();
			try {
				mes = Integer.parseInt(str);
				if (mes < 1 || mes > 12) {
					System.out.println("\nM�S INV�LIDO.");
					do {
						System.out.println("\nDESEJA CONTINUAR");
						System.out.println("1 - SIM");
						System.out.println("2 - N�O");
						System.out.print("OP��O: ");
						str = sc.nextLine();
						System.out.print("\n");
						try {
							resp = Integer.parseInt(str);
							if (resp < 1 || resp > 2) {
								System.out.println("\nOP��O INV�LIDA.\n");
								System.out.println("DIGITE OP��O V�LIDA.\n");
							} else if (resp == 2) {
								return;
							}
						} catch (NumberFormatException e) {

							System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						}
					} while (resp < 1 || resp > 2);

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			}
		} while (mes < 1 || mes > 12 || resp != 1);

		do {
			System.out.println("DIGITE O ANO: ");
			str = sc.nextLine();
			try {
				ano = Integer.parseInt(str);
				if (ano < 2017) {
					System.out.println("\nANO INV�LIDO.");
					System.out.println("O ANO DIGITADO DEVE SER O MAIOR OU IGUAL A 2017\n");
					do {
						System.out.println("DESEJA CONTINUAR");
						System.out.println("1 - SIM");
						System.out.println("2 - N�O");
						System.out.print("OP��O: ");
						str = sc.nextLine();
						System.out.print("\n");
						try {
							resp = Integer.parseInt(str);
							if (resp < 1 || resp > 2) {
								System.out.println("\nOP��O INV�LIDA.\n");
								System.out.println("DIGITE OP��O V�LIDA.\n");
							} else if (resp == 2) {
								return;
							}
						} catch (NumberFormatException e) {

							System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						}
					} while (resp < 1 || resp > 2);

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			}
		} while (ano < 2017 || resp != 1);
		while (aux < listaConta.size() && index == -1) {
			if (listaConta.get(aux).getCliente().getCodigo() == cliente.getCodigo()
					&& listaConta.get(aux).getVeiculo().getCodigo() == veiculo.getCodigo()
					&& listaConta.get(aux).getPatio().getCodigo() == patio.getCodigo()
					&& listaConta.get(aux).getMes() == mes && listaConta.get(aux).getAno() == ano) {
				index = listaConta.indexOf(listaConta.get(aux));
			} else {
				aux++;
			}
		}
		if (index >= 0) {
			if (listaConta.get(index).getDiarias() >= 1) {
				listaConta.get(index).setDiarias(listaConta.get(index).getDiarias() - 1);
				System.out.println("\nDI�RIA REMOVIDA COM SUCESSO.\n");
			} else {
				System.out.println("\nN�O H� DI�RIAS ADICIONADAS PARA ESTE CLIENTE.");
			}
		} else {
			System.out.println("\nN�O H� CONTA CADASTRADA COM ESTAS INFORMA��ES.");
		}
	}

	public void totalPagar() {
		index = -1;
		int mes = 0, opt, diaPg, ano = 0, aux = 0;
		String str;
		if (listaConta.isEmpty()) {
			System.out.println("\nNENHUMA CONTA CADASTRADA.\n");
		} else {
			System.out.println("\n--== [ FATURA ] ==--\n");
			if (listaCliente.isEmpty()) {
				System.out.println("\nN�O H� CLIENTES CADASTRADOS.\n");
				return;
			} else {
				// cliente = f2.retornaCliente(listaCliente);
				if (listaVeiculo.isEmpty()) {
					System.out.println("\nN�O H� VE�CULOS CADASTRADOS.\n");
					return;
				} else {
					// veiculo = f1.retornaVeiculo(listaVeiculo);
					if (listaPatio.isEmpty()) {
						System.out.println("\nN�O H� P�TIOS CADASTRADOS.\n");
						return;
					} else {
						// patio = f3.retornaPatio(listaPatio);
					}
				}

			}

			int resp = 1;
			do {
				System.out.println("DIGITE O M�S: ");
				str = sc.nextLine();
				try {
					mes = Integer.parseInt(str);
					if (mes < 1 || mes > 12) {
						System.out.println("\nM�S INV�LIDO.");
						do {
							System.out.println("DESEJA CONTINUAR");
							System.out.println("1 - SIM");
							System.out.println("2 - N�O");
							System.out.print("OP��O: ");
							str = sc.nextLine();
							System.out.print("\n");
							try {
								resp = Integer.parseInt(str);
								if (resp < 1 || resp > 2) {
									System.out.println("\nOP��O INV�LIDA.\n");
									System.out.println("DIGITE OP��O V�LIDA.\n");
								} else if (resp == 2) {
									return;
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
							}
						} while (resp < 1 || resp > 2);

					}
				} catch (NumberFormatException e) {

					System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				}
			} while (mes < 1 || mes > 12 || resp != 1);

			do {
				System.out.println("DIGITE O ANO: ");
				str = sc.nextLine();
				try {
					ano = Integer.parseInt(str);
					if (ano < 2017) {
						System.out.println("\nANO INV�LIDO.");
						System.out.println("O ANO DIGITADO DEVE SER O MAIOR OU IGUAL A 2017\n");
						do {
							System.out.println("DESEJA CONTINUAR");
							System.out.println("1 - SIM");
							System.out.println("2 - N�O");
							System.out.print("OP��O: ");
							str = sc.nextLine();
							System.out.print("\n");
							try {
								resp = Integer.parseInt(str);
								if (resp < 1 || resp > 2) {
									System.out.println("\nOP��O INV�LIDA.\n");
									System.out.println("DIGITE OP��O V�LIDA.\n");
								} else if (resp == 2) {
									return;
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
							}
						} while (resp < 1 || resp > 2);

					}
				} catch (NumberFormatException e) {

					System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				}
			} while (ano < 2017 || resp != 1);

			while (aux < listaConta.size() && index == -1) {
				if (listaConta.get(aux).getCliente().getCodigo() == cliente.getCodigo()
						&& listaConta.get(aux).getVeiculo().getCodigo() == veiculo.getCodigo()
						&& listaConta.get(aux).getPatio().getCodigo() == patio.getCodigo()
						&& listaConta.get(aux).getMes() == mes && listaConta.get(aux).getAno() == ano) {
					index = listaConta.indexOf(listaConta.get(aux));
				} else {
					aux++;
				}
			}

			if (index >= 0) {
				if (listaConta.get(index).getDiarias() == 0) {
					System.out.println("\nCLIENTE N�O POSSUI D�BITOS.\n");
				} else {
					System.out.println("--== [ INFORMA��ES DA FATURA ] ==--\n");
					System.out.println("C�DIGO: " + cliente.getCodigo());
					System.out.println("NOME: " + cliente.getNome());
					System.out.println("DI�RIAS: " + listaConta.get(index).getDiarias());
					System.out.print("\n");
					System.out.println("1 - PAGAMENTO TOTAL");
					System.out.println("2 - PAGAMENTO PARCELADO");
					System.out.println("3 - VOLTAR");
					System.out.print("OP��O: ");
					str = sc.nextLine();
					try {
						opt = Integer.parseInt(str);
						if (opt <= 0 || opt > 3) {
							System.out.println("\nO N�MERO DIGITADO EST� FORA DO INTERVALO DO MENU.\n");
						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						opt = 0;
					}
					switch (opt) {
					case 1:
						System.out.println("\nTODAS AS DI�RIAS FORAM PAGAS.");
						listaConta.get(index).setDiarias(0);
						listaConta.get(index).setPaga(true);
						break;
					case 2:
						System.out.println("\nQUANTIDADES DE DI�RIAS A PAGAR.");
						diaPg = sc.nextInt();
						sc.nextLine();
						if (listaConta.get(index).getDiarias() < diaPg) {
							System.out.println("\nA QUANTIDADE DIGITADA � MAIOR QUE O N�MERO DE DI�RIAS.\n");
						} else {
							listaConta.get(index).setDiarias(listaConta.get(index).getDiarias() - diaPg);
							System.out.print("FORAM PAGAS " + diaPg + " DI�RIAS\n");
						}
						break;
					case 3:
						break;
					}
				}
			}
		}
	}

	public void relatorio() {
		System.out.println("--== [ RELAT�RIO ] ==--");
		if (listaConta.isEmpty()) {
			System.out.println("\nN�O H� CONTAS CADASTRADAS.\n");
			return;
		}
		System.out.println("\n--== [ INFORMA��ES DA CONTA ] ==--\n");
		for (int i = 0; i < listaConta.size(); i++) {
			System.out.println(
					"DATA DO CADASTRO M�S/ANO: " + listaConta.get(i).getMes() + "/" + listaConta.get(i).getAno());
			System.out.println("C�DIGO DO VE�CULO: " + listaConta.get(i).getVeiculo().getCodigo());
			System.out.println("C�DIGO DO CLIENTE: " + listaConta.get(i).getCliente().getCodigo());
			System.out.println("CLIENTE: " + listaConta.get(i).getCliente().getNome());
			System.out.println("P�TIO: " + listaConta.get(i).getPatio().getCodigo());
			System.out.println("DI�RIAS: " + listaConta.get(i).getDiarias());
			System.out.println(
					"TOTAL R$ " + listaConta.get(i).getDiarias() * listaConta.get(i).getPatio().getValorDaDiaria());
			System.out.println("______________________________________\n");
		}
		System.out.println("\nTOTAL DE CONTAS CADASTRADAS " + listaConta.size());
		System.out.print("\n");
	}
}