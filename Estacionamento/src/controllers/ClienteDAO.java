package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Cliente;

import java.sql.ResultSet;

public class ClienteDAO {
	private Cliente cliente;
	Scanner sc;
	String sql;
	Connection con;
	PreparedStatement stm;

	public ClienteDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
	}

	public void addCliente() {
		Cliente cliente = new Cliente();
		System.out.println("\n--== [ CADASTRO DE CLIENTE ] ==--\n");
		System.out.println("NOME: ");
		cliente.setNome(sc.nextLine());
		System.out.println("LOGRADOURO: ");
		cliente.setLogradouro(sc.nextLine());
		System.out.println("NÚMERO: ");
		cliente.setNumero(sc.nextLine());
		System.out.println("BAIRRO: ");
		cliente.setBairro(sc.nextLine());
		System.out.println("MUNICÍPIO: ");
		cliente.setMunicipio(sc.nextLine());
		System.out.println("ESTADO: ");
		cliente.setEstado(sc.nextLine());
		System.out.println("CEP: ");
		cliente.setCep(sc.nextLine());
		System.out.println("TELEFONE: ");
		cliente.setTelefone(sc.nextLine());

		try {
			sql = "insert into clientes (nome, logradouro, numero, bairro, municipio, estado, cep, telefone) "
					+ "values (?,?,?,?,?,?,?,?)";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getLogradouro());
			stm.setString(3, cliente.getNumero());
			stm.setString(4, cliente.getBairro());
			stm.setString(5, cliente.getMunicipio());
			stm.setString(6, cliente.getEstado());
			stm.setString(7, cliente.getCep());
			stm.setString(8, cliente.getTelefone());
			stm.execute();
			stm.close();
			con.close();
			System.out.println("\nTRANSAÇÃO DE CADASTRO REALIZADA COM SUCESSO.\n");
		} catch (Exception e) {

			System.err.println(e);
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n");
		}

	}

	public void alteraCliente() {
		int codigo = 0, resp = 0, op2 = 0;
		String str, str2;
		boolean flag = false;
		System.out.println("--== [ ALTERAÇÃO DE CLIENTE ] ==--");
		System.out.println("\nINFORME O CÓDIGO DO CLIENTE.\n");
		System.out.print("CÓDIGO: ");
		str2 = sc.nextLine();
		try {
			codigo = Integer.parseInt(str2);
			if (codigo <= 0) {
				System.out.println("\nO CÓDIGO DIGITADO NÃO É VÁLIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
			flag = true;

		}

		try {
			sql = "select * from clientes where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				cliente = new Cliente(codigo, rs.getString("nome"), rs.getString("telefone"),
						rs.getString("logradouro"), rs.getString("numero"), rs.getString("bairro"),
						rs.getString("municipio"), rs.getString("cep"), rs.getString("estado"));

				cliente.imprimeCliente();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nNÃO HÁ CLIENTE CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
			return;
		}

		do {
			System.out.println("\n1 - ALTERAR TUDO");
			System.out.println("2 - ALTERAR INFORMAÇÃO ESPECÍFICA");
			System.out.println("3 - VOLTAR");
			System.out.print("OPÇÃO: ");
			str = sc.nextLine();
			try {
				resp = Integer.parseInt(str);
				if (resp <= 0 || resp > 3) {
					System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				resp = 0;

			}
			switch (resp) {

			case 1:
				System.out.println("\n--== [ ALTERAR INFORMAÇÕES ] ==--\n");
				System.out.println("NOME: ");
				cliente.setNome(sc.nextLine());
				System.out.println("LOGRADOURO: ");
				cliente.setLogradouro(sc.nextLine());
				System.out.println("NÚMERO: ");
				cliente.setNumero(sc.nextLine());
				System.out.println("BAIRRO: ");
				cliente.setBairro(sc.nextLine());
				System.out.println("MUNICÍPIO: ");
				cliente.setMunicipio(sc.nextLine());
				System.out.println("ESTADO: ");
				cliente.setEstado(sc.nextLine());
				System.out.println("CEP: ");
				cliente.setCep(sc.nextLine());
				System.out.println("TELEFONE: ");
				cliente.setTelefone(sc.nextLine());
				try {
					sql = "update clientes set nome = \'" + cliente.getNome() + "\', telefone = \'"
							+ cliente.getTelefone() + "\', logradouro = \'" + cliente.getLogradouro()
							+ "\', numero = \'" + cliente.getNumero() + "\', bairro = \'" + cliente.getBairro()
							+ "\', municipio = \'" + cliente.getMunicipio() + "\', cep = \'" + cliente.getCep()
							+ "\', estado = \'" + cliente.getEstado() + "\' where codigo = " + cliente.getCodigo();
					stm = con.prepareStatement(sql);
					stm.execute();
				} catch (SQLException e) {
					System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
				}

				System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
				break;
			case 2:
				do {
					System.out.println("\n--== [ ALTERAR INFORMAÇÃO ] ==--\n");
					System.out.println("1 - NOME");
					System.out.println("2 - LOGRADOURO");
					System.out.println("3 - NÚMERO");
					System.out.println("4 - BAIRRO");
					System.out.println("5 - MUNICÍPIO");
					System.out.println("6 - ESTADO");
					System.out.println("7 - CEP");
					System.out.println("8 - TELEFONE");
					System.out.println("9 - VOLTAR");
					System.out.print("OPÇÃO: ");
					str = sc.nextLine();
					System.out.print("\n");
					try {
						op2 = Integer.parseInt(str);
						if (op2 <= 0 || op2 > 9) {
							System.out.println("\nO NÚMERO DIGITADO ESTÁ FORA DO INTERVALO DO MENU.\n");

						}
					} catch (NumberFormatException e) {

						System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
						op2 = 0;

					}
					switch (op2) {

					case 1:
						System.out.println("NOME: ");
						cliente.setNome(sc.nextLine());
						try {
							sql = "update clientes set nome = \'" + cliente.getNome() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 2:
						System.out.println("LOGRADOURO: ");
						cliente.setLogradouro(sc.nextLine());
						try {
							sql = "update clientes set logradouro = \'" + cliente.getLogradouro() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 3:
						System.out.println("NÚMERO: ");
						cliente.setNumero(sc.nextLine());
						try {
							sql = "update clientes set numero = \'" + cliente.getNumero() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 4:
						System.out.println("BAIRRO: ");
						cliente.setBairro(sc.nextLine());
						try {
							sql = "update clientes set bairro = \'" + cliente.getBairro() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 5:
						System.out.println("MUNICÍPIO: ");
						cliente.setMunicipio(sc.nextLine());
						try {
							sql = "update clientes set municipio = \'" + cliente.getMunicipio() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 6:
						System.out.println("ESTADO: ");
						cliente.setEstado(sc.nextLine());
						try {
							sql = "update clientes set estado = \'" + cliente.getEstado() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 7:
						System.out.println("CEP: ");
						cliente.setCep(sc.nextLine());
						try {
							sql = "update clientes set cep = \'" + cliente.getCep() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 8:
						System.out.println("TELEFONE: ");
						cliente.setTelefone(sc.nextLine());
						try {
							sql = "update clientes set telefone = \'" + cliente.getTelefone() + "\'where codigo = "
									+ cliente.getCodigo();
							stm = con.prepareStatement(sql);
							stm.execute();
						} catch (SQLException e) {
							System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
							return;
						}
						System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");
						break;
					case 9:
						break;
					}

				} while (op2 != 9);
				flag = true;
				break;

			}

		} while (resp != 3);

		if (!flag) {
			System.out.println("\nNÃO HÁ CLIENTE CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
		}

	}

	public void removeCliente() {
		int codigo = 0, resp = 0;
		String str;
		boolean flag = false;
		System.out.println("\n--== [ EXCLUSÃO DE CLIENTE ] ==--\n");

		System.out.println("\nINFORME O CÓDIGO DO CLIENTE.\n");
		System.out.print("CÓDIGO: ");
		str = sc.nextLine();
		try {
			codigo = Integer.parseInt(str);
			if (codigo <= 0) {
				System.out.println("\nO CÓDIGO DIGITADO NÃO É VÁLIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
			flag = true;

		}
		try {
			sql = "select * from clientes where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				cliente = new Cliente(codigo, rs.getString("nome"), rs.getString("telefone"),
						rs.getString("logradouro"), rs.getString("numero"), rs.getString("bairro"),
						rs.getString("municipio"), rs.getString("cep"), rs.getString("estado"));

				cliente.imprimeCliente();
				flag = true;

			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}
		if (!flag) {
			System.out.println("\nNÃO HÁ CLIENTE CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
			return;
		}

		System.out.println("\nEXCLUIR 1 - SIM / 2 - NÃO");
		System.out.print("OPÇÃO: ");
		str = sc.nextLine();
		try {
			resp = Integer.parseInt(str);
			if (resp <= 0) {
				System.out.println("\nO CÓDIGO DIGITADO NÃO É VÁLIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
			flag = true;
		}
		if (resp == 1) {
			try {
				sql = "delete from clientes where codigo = " + codigo;
				stm = con.prepareStatement(sql);
				stm.execute();
				System.out.println("\nTRANSAÇÃO DE EXCLUSÃO REALIZADA COM SUCESSO.\n");
				flag = true;
			} catch (SQLException ex) {
				System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
			}

		} else {
			System.out.println("\nTRANSAÇÃO DE EXCLUSÃO NÃO REALIZADA.\n");
			flag = true;
		}

	}

	public void consultaCliente() {
		int codigo = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CONSULTAR CLIENTE ] ==--\n");
		System.out.println("\nINFORME O CÓDIGO DO CLIENTE.\n");
		System.out.print("CÓDIGO: ");
		str = sc.nextLine();
		try {
			codigo = Integer.parseInt(str);
			if (codigo <= 0) {
				System.out.println("\nO CÓDIGO DIGITADO NÃO É VÁLIDO.\n");
				flag = true;
			}
		} catch (NumberFormatException e) {

			System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
			flag = true;

		}

		try {
			sql = "select * from clientes where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				cliente = new Cliente(codigo, rs.getString("nome"), rs.getString("telefone"),
						rs.getString("logradouro"), rs.getString("numero"), rs.getString("bairro"),
						rs.getString("municipio"), rs.getString("cep"), rs.getString("estado"));

				cliente.imprimeCliente();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nNÃO HÁ CLIENTE CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
		}

	}

	public void relatorioCliente() {
		System.out.println("\n--== [ RELATÓRIO ] ==--\n");
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null;
		try {
			String sql = "select * from clientes order by codigo";
			con = Conexao.getConexao();
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();
				try {
					cli.setNome(rs.getString("nome"));
					cli.setBairro(rs.getString("bairro"));
					cli.setCep(rs.getString("cep"));
					cli.setCodigo(rs.getInt("codigo"));
					cli.setEstado(rs.getString("estado"));
					cli.setLogradouro(rs.getString("logradouro"));
					cli.setMunicipio(rs.getString("municipio"));
					cli.setNumero(rs.getString("numero"));
					cli.setTelefone(rs.getString("telefone"));
				} catch (Exception e) {
					rs.close();
				}
				clientes.add(cli);
			}
		} catch (Exception e) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n");
		}

		for (Cliente c : clientes) {
			c.imprimeCliente();
		}
		System.out.println("\nTOTAL DE CLIENTES CADASTRADOS " + clientes.size());
	}

}