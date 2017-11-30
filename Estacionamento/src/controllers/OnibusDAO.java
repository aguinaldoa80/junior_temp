package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Onibus;
import models.Veiculo;

public class OnibusDAO {

	private Onibus onibus;
	Scanner sc;
	String sql;
	Connection con;
	PreparedStatement stm;

	public OnibusDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
	}

	public void adicionaOnibus() {
		onibus = new Onibus();
		int anoF = 0, anoM = 0, caPass = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CADASTRO DE ÔNIBUS ] ==--\n");
		System.out.println("MARCA: ");
		onibus.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		onibus.setModelo(sc.nextLine());
		do {
			System.out.println("ANO DA FABRICAÇÃO: ");
			str = sc.nextLine();
			try {
				anoF = Integer.parseInt(str);
				flag = true;
				if (anoF < 1895) {
					System.out.println("\nO ANO DE FABRICAÇÃO DIGITADO NÃO É VÁLIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR A 1895.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		onibus.setAnoFabricacao(anoF);
		do {
			flag = false;
			System.out.println("ANO DO MODELO: ");
			str = sc.nextLine();
			try {
				anoM = Integer.parseInt(str);
				flag = true;
				if (anoM < anoF) {
					System.out.println("\nO ANO DO MODELO NÃO É VÁLIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR AO ANO DE FABRICAÇÃO.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		onibus.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		onibus.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		onibus.setPlaca(sc.nextLine());
		do {
			System.out.println("CAPACIDADE DE PASSAGEIROS: ");
			str = sc.nextLine();
			try {
				caPass = Integer.parseInt(str);
				flag = true;
				if (caPass <= 0) {
					System.out.println("\nA CAPACIDADE DE PASSAGEIROS DEVE SER MAIOR QUE 0.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		onibus.setCapacidadePassageiros(caPass);
		try {
			sql = "insert into veiculos (marca, modelo, anofabricacao, anomodelo, chassi, placa, capacidadepassageiros) "
					+ "values (?,?,?,?,?,?,?)";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setString(1, onibus.getMarca());
			stm.setString(2, onibus.getModelo());
			stm.setInt(3, onibus.getAnoFabricacao());
			stm.setInt(4, onibus.getAnoModelo());
			stm.setString(5, onibus.getChassi());
			stm.setString(6, onibus.getPlaca());
			stm.setInt(7, onibus.getCapacidadePassageiros());
			stm.execute();
			stm.close();
			con.close();
			System.out.println("\nTRANSAÇÃO DE CADASTRO REALIZADA COM SUCESSO.\n");
		} catch (Exception e) {

			System.err.println(e);
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n");
		}

	}

	public void alteraOnibus() {
		int codigo = 0, anoF = 0, anoM = 0, caPass = 0;
		String str;
		boolean flag = false;
		System.out.println("--== [ ALTERAÇÃO DE ÔNIBUS ] ==--");
		System.out.println("\nINFORME O CÓDIGO DO ÔNIBUS.\n");
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
			sql = "select * from veiculos where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				onibus = new Onibus(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("capacidadepassageiros"));

				if (rs.getFloat("capacidadecarga") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("quantidadeportas") > 0) {
					System.out.println("\nO CÓDIGO INFORMADO NÃO PERTENCE HÁ UM ÔNIBUS\n");
					return;
				}
				onibus.imprimeVeiculo();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nNÃO HÁ ÔNIBUS CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
			return;
		}

		System.out.println("\n--== [ ALTERAÇÃO DE ÔNIBUS ] ==--\n");
		System.out.println("MARCA: ");
		onibus.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		onibus.setModelo(sc.nextLine());
		do {
			System.out.println("ANO DA FABRICAÇÃO: ");
			str = sc.nextLine();
			try {
				anoF = Integer.parseInt(str);
				flag = true;
				if (anoF < 1930) {
					System.out.println("\nO ANO DE FABRICAÇÃO DIGITADO NÃO É VÁLIDO.");
					System.out.println("O ANO DEVE SER SUPERIOR A 1930.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		onibus.setAnoFabricacao(anoF);
		do {
			flag = false;
			System.out.println("ANO DO MODELO: ");
			str = sc.nextLine();
			try {
				anoM = Integer.parseInt(str);
				flag = true;
				if (anoM < anoF) {
					System.out.println("\nO ANO DO MODELO NÃO É VÁLIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR AO ANO DE FABRICAÇÃO.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		onibus.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		onibus.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		onibus.setPlaca(sc.nextLine());
		do {
			System.out.println("CAPACIDADE DE PASSAGEIROS: ");
			str = sc.nextLine();
			try {
				caPass = Integer.parseInt(str);
				flag = true;
				if (caPass <= 0) {
					System.out.println("\nA CAPACIDADE DE PASSAGEIROS DEVE SER MAIOR QUE ZERO.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		onibus.setCapacidadePassageiros(caPass);

		try {
			sql = "update veiculos set marca = \'" + onibus.getMarca() + "\', modelo = \'" + onibus.getModelo()
					+ "\', anofabricacao = \'" + onibus.getAnoFabricacao() + "\', anomodelo = \'"
					+ onibus.getAnoModelo() + "\', placa = \'" + onibus.getPlaca() + "\', chassi = \'"
					+ onibus.getChassi() + "\', capacidadepassageiros = \'" + onibus.getCapacidadePassageiros()
					+ "\' where codigo = " + onibus.getCodigo();
			stm = con.prepareStatement(sql);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
		}

		System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");

	}

	public void removeOnibus() {
		int codigo = 0, resp = 0;
		String str;
		boolean flag = false;
		System.out.println("\n--== [ EXCLUSÃO DE ÔNIBUS ] ==--\n");
		System.out.println("\nINFORME O CÓDIGO DO ÔNIBUS.\n");
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
			sql = "select * from veiculos where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				onibus = new Onibus(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("capacidadepassageiros"));

				if (rs.getFloat("capacidadecarga") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("quantidadeportas") > 0) {
					System.out.println("\nO CÓDIGO INFORMADO NÃO PERTENCE HÁ UM ÔNIBUS.\n");
					return;
				}
				onibus.imprimeVeiculo();
				flag = true;

			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}
		if (!flag) {
			System.out.println("\nNÃO HÁ ÔNIBUS CADASTRADO COM O CÓDIGO " + codigo);
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
				sql = "delete from veiculos where codigo = " + codigo;
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

	public void relatorioOnibus() {
		System.out.println("--== [ RELATÓRIO ] ==--");
		ArrayList<Veiculo> onibus = new ArrayList<Veiculo>();
		ResultSet rs = null;
		try {
			String sql = "select * from veiculos";
			con = Conexao.getConexao();
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Onibus oni = new Onibus();
				try {
					oni.setCodigo(rs.getInt("codigo"));
					oni.setMarca(rs.getString("marca"));
					oni.setModelo(rs.getString("modelo"));
					oni.setAnoFabricacao(rs.getInt("anofabricacao"));
					oni.setAnoModelo(rs.getInt("anomodelo"));
					oni.setChassi(rs.getString("chassi"));
					oni.setPlaca(rs.getString("placa"));
					oni.setCapacidadePassageiros(rs.getInt("capacidadepassageiros"));

				} catch (Exception e) {

				}
				if (rs.getInt("capacidadepassageiros") > 0) {
					onibus.add(oni);
				}
			}
		} catch (Exception e) {

		}

		try {
			for (Veiculo oni : onibus) {
				if (oni instanceof Onibus) {
					oni.imprimeVeiculo();
				}
			}
			System.out.println("\nTOTAL DE ÔNIBUS CADASTRADOS " + onibus.size());

		} catch (Exception e) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n");
		}
	}

	public void consultaOnibus() {
		int codigo = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CONSULTAR ÔNIBUS ] ==--\n");
		System.out.println("\nINFORME O CÓDIGO DO ÔNIBUS.\n");
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
			sql = "select * from veiculos where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				onibus = new Onibus(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("capacidadepassageiros"));

				if (rs.getInt("capacidadecarga") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("quantidadeportas") > 0) {
					System.out.println("\nO CÓDIGO INFORMADO NÃO PERTENCE HÁ UM ÔNIBUS.\n");
					return;

				}
				if (onibus instanceof Onibus) {
					onibus.imprimeVeiculo();
					flag = true;
				}

			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nNÃO HÁ ÔNIBUS CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
		}

	}

}
