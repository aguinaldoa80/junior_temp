package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Patio;

public class PatioDAO {

	private Patio patio;
	Scanner sc;
	String sql;
	Connection con;
	PreparedStatement stm;

	public PatioDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
	}

	public void addPatio() {

		String valor, str;
		int capacidadeDeVeiculos = 0;
		float valorDaDiaria = 0;
		boolean flag = false;
		Patio patio = new Patio();
		System.out.println("\n--== [ CADASTRO DE P�TIO ] ==--\n");
		System.out.println("NOME: ");
		patio.setNome(sc.nextLine());
		System.out.println("LOGRADOURO: ");
		patio.setLogradouro(sc.nextLine());
		System.out.println("N�MERO: ");
		patio.setNumero(sc.nextLine());
		System.out.println("BAIRRO: ");
		patio.setBairro(sc.nextLine());
		System.out.println("MUNIC�PIO: ");
		patio.setMunicipio(sc.nextLine());
		System.out.println("ESTADO: ");
		patio.setEstado(sc.nextLine());
		System.out.println("CEP: ");
		patio.setCep(sc.nextLine());
		do {
			flag = false;
			System.out.println("CAPACIDADE DE VE�CULOS: ");
			str = sc.nextLine();
			try {
				capacidadeDeVeiculos = Integer.parseInt(str);
				patio.setCapacidadeDeVeiculos(capacidadeDeVeiculos);
				flag = true;
				if (capacidadeDeVeiculos <= 0) {
					System.out.println("\nA CAPACIDADE DE VE�CULOS DEVE SER MAIOR QUE ZERO.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		do {
			System.out.println("VALOR DA DI�RIA: ");
			valor = sc.nextLine();
			try {
				valorDaDiaria = Float.parseFloat(valor);
				patio.setValorDaDiaria(valorDaDiaria);
				flag = true;
				if (valorDaDiaria <= 0) {
					System.out.println("\nO VALOR DA DI�RIA PRECISA SER ACIMA DE ZERO.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		System.out.println("LOTA��O: ");
		patio.setLotacao(sc.nextInt());
		sc.nextLine();

		try {
			sql = "insert into patios (nome, logradouro, numero, bairro, municipio, estado, cep, capacidadedeveiculos, valordadiaria,lotacao) "
					+ "values (?,?,?,?,?,?,?,?,?,?)";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setString(1, patio.getNome());
			stm.setString(2, patio.getLogradouro());
			stm.setString(3, patio.getNumero());
			stm.setString(4, patio.getBairro());
			stm.setString(5, patio.getMunicipio());
			stm.setString(6, patio.getEstado());
			stm.setString(7, patio.getCep());
			stm.setInt(8, patio.getCapacidadeDeVeiculos());
			stm.setFloat(9, patio.getValorDaDiaria());
			stm.setInt(10, patio.getLotacao());
			stm.execute();
			stm.close();
			con.close();
			System.out.println("\nTRANSA��O DE CADASTRO REALIZADA COM SUCESSO.\n");
		} catch (Exception e) {

			System.err.println(e);
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n");
		}

	}

	public void relatorioPatio() {
		System.out.println("\n--== [ RELAT�RIO ] ==--\n");
		ArrayList<Patio> patios = new ArrayList<Patio>();
		ResultSet rs = null;
		try {
			String sql = "select * from patios order by codigo";
			con = Conexao.getConexao();
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Patio pat = new Patio();
				try {
					pat.setNome(rs.getString("nome"));
					pat.setBairro(rs.getString("bairro"));
					pat.setCep(rs.getString("cep"));
					pat.setCodigo(rs.getInt("codigo"));
					pat.setEstado(rs.getString("estado"));
					pat.setLogradouro(rs.getString("logradouro"));
					pat.setMunicipio(rs.getString("municipio"));
					pat.setNumero(rs.getString("numero"));
					pat.setCapacidadeDeVeiculos(rs.getInt("capacidadedeveiculos"));
					pat.setValorDaDiaria(rs.getFloat("valordadiaria"));
					pat.setLotacao(rs.getInt("lotacao"));
				} catch (Exception e) {
					rs.close();
				}
				patios.add(pat);
			}

		} catch (Exception e) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n");
		}

		for (Patio p : patios) {
			p.imprimePatio();
		}
		System.out.println("\nTOTAL DE P�TIOS CADASTRADOS " + patios.size());
	}

	public void alteraPatio() {
		int codigo = 0, resp = 0, op2 = 0, capV = 0;
		String str, str2, valor;
		float valorDaDiaria = 0;
		boolean flag = false;
		System.out.println("\n--== [ ALTERA��O DE P�TIO ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DO P�TIO.\n");
		System.out.print("C�DIGO: ");
		str2 = sc.nextLine();
		try {
			codigo = Integer.parseInt(str2);
			if (codigo <= 0) {
				System.out.println("\nO C�DIGO DIGITADO N�O � V�LIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			flag = true;

		}

		try {
			sql = "select * from patios where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				patio = new Patio(codigo, rs.getString("nome"), rs.getString("logradouro"), rs.getString("numero"),
						rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"), rs.getString("cep"),
						rs.getInt("capacidadedeveiculos"), rs.getFloat("valordadiaria"), rs.getInt("lotacao"));

				patio.imprimePatio();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nN�O H� P�TIO CADASTRADO COM O C�DIGO " + codigo);
			System.out.print("\n");
			return;
		}

		flag = true;
		do {
			System.out.println("1 - ALTERAR TUDO");
			System.out.println("2 - ALTERAR INFORMA��O ESPEC�FICA");
			System.out.println("3 - VOLTAR");
			System.out.print("OP��O: ");
			str = sc.nextLine();
			try {
				resp = Integer.parseInt(str);
				if (resp <= 0 || resp > 3) {
					System.out.println("\nO N�MERO DIGITADO EST� FORA DO INTERVALO DO MENU.\n");

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");

			}
			switch (resp) {

			case 1:
				System.out.println("\n--== [ ALTERAR INFORMA��ES P�TIO] ==--\n");
				System.out.println("NOME: ");
				patio.setNome(sc.nextLine());
				System.out.println("LOGRADOURO: ");
				patio.setLogradouro(sc.nextLine());
				System.out.println("N�MERO: ");
				patio.setNumero(sc.nextLine());
				System.out.println("BAIRRO: ");
				patio.setBairro(sc.nextLine());
				System.out.println("MUNIC�PIO: ");
				patio.setMunicipio(sc.nextLine());
				System.out.println("ESTADO: ");
				patio.setEstado(sc.nextLine());
				System.out.println("CEP: ");
				patio.setCep(sc.nextLine());
				do {
					flag = false;
					System.out.println("CAPACIDADE DE VE�CULOS: ");
					str = sc.nextLine();
					try {
						capV = Integer.parseInt(str);
						flag = true;
						if (capV <= 0) {
							System.out.println("\nA CAPACIDADE DE VE�CULOS DEVE SER MAIOR QUE ZERO.\n");
							flag = false;

						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						flag = false;

					}
				} while (!flag);
				patio.setCapacidadeDeVeiculos(capV);
				do {
					System.out.println("VALOR DA DI�RIA: ");
					valor = sc.nextLine();
					try {
						valorDaDiaria = Float.parseFloat(valor);
						flag = true;
						if (valorDaDiaria <= 0) {
							System.out.println("\nO VALOR DA DI�RIA PRECISA SER ACIMA DE ZERO.\n");
							flag = false;
						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
						flag = false;

					}
				} while (!flag);
				patio.setValorDaDiaria(valorDaDiaria);
				System.out.println("LOTA��O: ");
				patio.setLotacao(sc.nextInt());
				sc.nextLine();
				try {
					sql = "update patios set nome = \'" + patio.getNome() + "\', logradouro = \'"
							+ patio.getLogradouro() + "\', numero = \'" + patio.getNumero() + "\', bairro = \'"
							+ patio.getBairro() + "\', municipio = \'" + patio.getMunicipio() + "\', estado = \'"
							+ patio.getEstado() + "\', cep = \'" + patio.getCep() + "\', capacidadedeveiculos = \'"
							+ patio.getCapacidadeDeVeiculos() + "\', valordadiaria = \'" + patio.getValorDaDiaria()
							+ "\', lotacao = \'" + patio.getLotacao() + "\' where codigo = " + patio.getCodigo();
					stm = con.prepareStatement(sql);
					stm.execute();
				} catch (SQLException e) {
					System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
				}

				System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n\n");
				break;
			case 2:
				do {
					System.out.println("\n--== [ ALTERAR INFORMA��O ] ==--\n");
					System.out.println("1  - NOME");
					System.out.println("2  - LOGRADOURO");
					System.out.println("3  - N�MERO");
					System.out.println("4  - BAIRRO");
					System.out.println("5  - MUNIC�PIO");
					System.out.println("6  - ESTADO");
					System.out.println("7  - CEP");
					System.out.println("8  - CAPACIDADE DE VE�CULOS");
					System.out.println("9  - VALOR DA DI�RIA");
					System.out.println("10 - LOTA��O");
					System.out.println("11 - VOLTAR");
					System.out.print("OP��O: ");
					str = sc.nextLine();
					System.out.print("\n");
					try {
						op2 = Integer.parseInt(str);
						if (op2 <= 0 || op2 > 11) {
							System.out.println("\nO N�MERO DIGITADO EST� FORA DO INTERVALO DO MENU.\n");

						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");

					}
					switch (op2) {

					case 1:
						System.out.println("NOME: ");
						patio.setNome(sc.nextLine());
						try {
							sql = "update patios set nome = \'" + patio.getNome() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 2:
						System.out.println("LOGRADOURO: ");
						patio.setLogradouro(sc.nextLine());
						try {
							sql = "update patios set logradouro = \'" + patio.getLogradouro() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 3:
						System.out.println("N�MERO: ");
						patio.setNumero(sc.nextLine());
						try {
							sql = "update patios set numero = \'" + patio.getNumero() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 4:
						System.out.println("BAIRRO: ");
						patio.setBairro(sc.nextLine());
						try {
							sql = "update patios set bairro = \'" + patio.getBairro() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 5:
						System.out.println("MUNIC�PIO: ");
						patio.setMunicipio(sc.nextLine());
						try {
							sql = "update patios set municipio = \'" + patio.getMunicipio() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 6:
						System.out.println("ESTADO: ");
						patio.setEstado(sc.nextLine());
						try {
							sql = "update patios set estado = \'" + patio.getEstado() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 7:
						System.out.println("CEP: ");
						patio.setCep(sc.nextLine());
						try {
							sql = "update patios set cep = \'" + patio.getCep() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 8:
						do {
							flag = false;
							System.out.println("CAPACIDADE DE VE�CULOS: ");
							str = sc.nextLine();
							try {
								capV = Integer.parseInt(str);
								flag = true;
								if (capV <= 0) {
									System.out.println("\nA CAPACIDADE DE VE�CULOS DEVE SER MAIOR QUE ZERO.\n");
									flag = false;

								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
								flag = false;

							}
						} while (!flag);
						patio.setCapacidadeDeVeiculos(capV);
						try {
							sql = "update patios set capacidadedeveiculos = \'" + patio.getCapacidadeDeVeiculos()
									+ "\'where codigo = " + patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 9:
						do {
							System.out.println("VALOR DA DI�RIA: ");
							valor = sc.nextLine();
							try {
								valorDaDiaria = Float.parseFloat(valor);
								flag = true;
								if (valorDaDiaria <= 0) {
									System.out.println("\nO VALOR DA DI�RIA PRECISA SER ACIMA DE ZERO.\n");
									flag = false;
								}
							} catch (NumberFormatException e) {

								System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
								flag = false;

							}
						} while (!flag);
						patio.setValorDaDiaria(valorDaDiaria);
						try {
							sql = "update patios set valordadiaria = \'" + patio.getValorDaDiaria()
									+ "\'where codigo = " + patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 10:
						System.out.println("LOTA��O: ");
						patio.setLotacao(sc.nextInt());
						sc.nextLine();
						try {
							sql = "update patios set lotacao = \'" + patio.getLotacao() + "\'where codigo = "
									+ patio.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
						break;
					case 11:
						break;
					}

				} while (op2 != 11);
				flag = true;
				break;

			}

		} while (resp != 3);

		if (!flag) {
			System.out.println("\nN�O H� P�TIO CADASTRADO COM O C�DIGO " + codigo);
			System.out.print("\n");
		}

	}

	public void removePatio() {
		int codigo = 0, resp = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ EXCLUS�O DE P�TIO ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DO P�TIO.\n");
		System.out.print("C�DIGO: ");
		str = sc.nextLine();
		try {
			codigo = Integer.parseInt(str);
			if (codigo <= 0) {
				System.out.println("\nO C�DIGO DIGITADO N�O � V�LIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			flag = true;

		}
		try {
			sql = "select * from patios where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				patio = new Patio(codigo, rs.getString("nome"), rs.getString("logradouro"), rs.getString("numero"),
						rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"), rs.getString("cep"),
						rs.getInt("capacidadedeveiculos"), rs.getFloat("valordadiaria"), rs.getInt("lotacao"));

				patio.imprimePatio();
				flag = true;
			}

		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}
		if (!flag) {
			System.out.println("\nN�O H� P�TIO CADASTRADO COM O C�DIGO " + codigo);
			System.out.print("\n");
			return;
		}
		System.out.println("\nEXCLUIR 1 - SIM / 2 - N�O");
		System.out.print("OP��O: ");
		str = sc.nextLine();
		try {
			resp = Integer.parseInt(str);
			if (resp <= 0) {
				System.out.println("\nO C�DIGO DIGITADO N�O � V�LIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			flag = true;
		}
		if (resp == 1) {
			try {
				sql = "delete from patios where codigo = " + codigo;
				stm = con.prepareStatement(sql);
				stm.execute();
				System.out.println("\nTRANSA��O DE EXCLUS�O REALIZADA COM SUCESSO.\n");
				flag = true;
			} catch (SQLException ex) {
				System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
			}

		} else {
			System.out.println("\nTRANS�O DE EXCLUS�O N�O REALIZADA.\n");
			flag = true;
		}

	}

	public void consultaPatio() {
		int codigo = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CONSULTAR P�TIO ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DO P�TIO.\n");
		System.out.print("C�DIGO: ");
		str = sc.nextLine();
		try {
			codigo = Integer.parseInt(str);
			if (codigo <= 0) {
				System.out.println("\nO C�DIGO DIGITADO N�O � V�LIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
			flag = true;

		}

		try {
			sql = "select * from patios where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				patio = new Patio(codigo, rs.getString("nome"), rs.getString("logradouro"), rs.getString("numero"),
						rs.getString("bairro"), rs.getString("municipio"), rs.getString("estado"), rs.getString("cep"),
						rs.getInt("capacidadedeveiculos"), rs.getFloat("valordadiaria"), rs.getInt("lotacao"));

				patio.imprimePatio();

				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nN�O H� P�TIO CADASTRADO COM O C�DIGO " + codigo);
			System.out.print("\n");
		}
	}
}