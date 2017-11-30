package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Caminhao;
import models.Veiculo;

public class CaminhaoDAO {

	private Caminhao caminhao;
	Scanner sc;
	String sql = "";
	Connection con;
	PreparedStatement stm;

	public CaminhaoDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
	}

	public void adicionaCaminhao() {
		caminhao = new Caminhao();
		int anoF = 0, anoM = 0;
		float capCarg = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CADASTRO DE CAMINHÃO ] ==--\n");
		System.out.println("MARCA: ");
		caminhao.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		caminhao.setModelo(sc.nextLine());
		do {
			System.out.println("ANO DA FABRICAÇÃO: ");
			str = sc.nextLine();
			try {
				anoF = Integer.parseInt(str);
				flag = true;
				if (anoF < 1885) {
					System.out.println("\nO ANO DE FABRICAÇÃO DIGITADO NÃO É VÁLIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR A 1885.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		caminhao.setAnoFabricacao(anoF);
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
		caminhao.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		caminhao.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		caminhao.setPlaca(sc.nextLine());
		do {
			System.out.println("CAPACIDADE DE CARGA: ");
			str = sc.nextLine();
			try {
				capCarg = Float.parseFloat(str);
				flag = true;
				if (capCarg <= 0) {
					System.out.println("\nA CAPACIDADE DE CARGA DEVE SER MAIOR QUE ZERO.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		caminhao.setCapacidadeCarga(capCarg);

		try {
			sql = "insert into veiculos (marca, modelo, anofabricacao, anomodelo, chassi, placa, capacidadecarga) "
					+ "values (?,?,?,?,?,?,?)";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setString(1, caminhao.getMarca());
			stm.setString(2, caminhao.getModelo());
			stm.setInt(3, caminhao.getAnoFabricacao());
			stm.setInt(4, caminhao.getAnoModelo());
			stm.setString(5, caminhao.getChassi());
			stm.setString(6, caminhao.getPlaca());
			stm.setFloat(7, caminhao.getCapacidadeCarga());
			stm.execute();
			stm.close();
			con.close();
			System.out.println("\nTRANSAÇÃO DE CADASTRO REALIZADA COM SUCESSO.");
		} catch (Exception e) {

			System.err.println(e);
			System.out.println("TRANSAÇÃO DE CADASTRO NÃO REALIZADA.\n");
		}

	}

	public void alteraCaminhao() {
		int codigo = 0, anoF = 0, anoM = 0;
		String str;
		boolean flag = false;
		float capCarg = 0;
		System.out.println("--== [ ALTERAÇÃO DE CAMINHÃO ] ==--");
		System.out.println("\nINFORME O CÓDIGO DO CAMINHÃO.\n");
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
				caminhao = new Caminhao(codigo, rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("anofabricacao"), rs.getInt("anomodelo"), rs.getString("placa"),
						rs.getString("chassi"), rs.getFloat("capacidadecarga"));

				if (rs.getInt("quantidadeportas") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO CÓDIGO INFORMADO NÃO PERTENCE HÁ UM CAMINHÃO\n");
					return;
				}
				caminhao.imprimeVeiculo();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nNÃO HÁ CAMINHÃO CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
			return;
		}

		System.out.println("\n--== [ ALTERAR INFORMAÇÕES CAMINHÃO ] ==--\n");
		System.out.println("MARCA: ");
		caminhao.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		caminhao.setModelo(sc.nextLine());
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
		caminhao.setAnoFabricacao(anoF);
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
		caminhao.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		caminhao.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		caminhao.setPlaca(sc.nextLine());
		do {
			flag = false;
			System.out.println("CAPACIDADE DE CARGA: ");
			str = sc.nextLine();
			try {
				capCarg = Float.parseFloat(str);
				flag = true;
				if (capCarg <= 0) {
					System.out.println("\nA CAPACIDADE DE CARGA DEVE SER MAIOR QUE 0.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS NÃO SÃO PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		caminhao.setCapacidadeCarga(capCarg);
		try {
			sql = "update veiculos set marca = \'" + caminhao.getMarca() + "\', modelo = \'" + caminhao.getModelo()
					+ "\', anofabricacao = \'" + caminhao.getAnoFabricacao() + "\', anomodelo = \'"
					+ caminhao.getAnoModelo() + "\', placa = \'" + caminhao.getPlaca() + "\', chassi = \'"
					+ caminhao.getChassi() + "\', capacidadecarga = \'" + caminhao.getCapacidadeCarga()
					+ "\' where codigo = " + caminhao.getCodigo();
			stm = con.prepareStatement(sql);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + e);
		}

		System.out.println("\nTRANSAÇÃO DE ALTERAÇÃO REALIZADA COM SUCESSO.\n");

	}

	public void removeCaminhao() {
		int codigo = 0, resp = 0;
		String str;
		boolean flag = false;
		System.out.println("\n--== [ EXCLUSÃO DE CAMINHÃO ] ==--\n");
		System.out.println("\nINFORME O CÓDIGO DO CAMINHÃO.\n");
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
				caminhao = new Caminhao(codigo, rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("anofabricacao"), rs.getInt("anomodelo"), rs.getString("placa"),
						rs.getString("chassi"), rs.getFloat("capacidadecarga"));

				if (rs.getInt("quantidadeportas") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO CÓDIGO INFORMADO NÃO PERTENCE HÁ UM CAMINHÃO.\n");
					return;
				}
				caminhao.imprimeVeiculo();
				flag = true;

			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}
		if (!flag) {
			System.out.println("\nNÃO HÁ CAMINHÃO CADASTRADO COM O CÓDIGO " + codigo);
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

	public void relatorioCaminhao() {
		System.out.println("--== [ RELATÓRIO ] ==--");
		ArrayList<Veiculo> caminhoes = new ArrayList<Veiculo>();
		ResultSet rs = null;
		try {
			String sql = "select * from veiculos";
			con = Conexao.getConexao();
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Caminhao cam = new Caminhao();
				try {
					cam.setCodigo(rs.getInt("codigo"));
					cam.setMarca(rs.getString("marca"));
					cam.setModelo(rs.getString("modelo"));
					cam.setAnoFabricacao(rs.getInt("anofabricacao"));
					cam.setAnoModelo(rs.getInt("anomodelo"));
					cam.setChassi(rs.getString("chassi"));
					cam.setPlaca(rs.getString("placa"));
					cam.setCapacidadeCarga(rs.getFloat("capacidadecarga"));

				} catch (Exception e) {

				}
				if (rs.getFloat("capacidadecarga") > 0) {
					caminhoes.add(cam);
				}
			}
		} catch (Exception e) {

		}

		try {
			for (Veiculo cam : caminhoes) {
				if (cam instanceof Caminhao) {
					cam.imprimeVeiculo();
				}

			}
			System.out.println("\nTOTAL DE CAMINHÕES CADASTRADOS " + caminhoes.size());

		} catch (Exception e) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n");
		}
	}

	public void consultaCaminhao() {
		int codigo = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CONSULTAR CAMINHÃO ] ==--\n");
		System.out.println("\nINFORME O CÓDIGO DO CAMINHÃO.\n");
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
				caminhao = new Caminhao(codigo, rs.getString("marca"), rs.getString("modelo"),
						rs.getInt("anofabricacao"), rs.getInt("anomodelo"), rs.getString("placa"),
						rs.getString("chassi"), rs.getFloat("capacidadecarga"));

				if (rs.getInt("quantidadeportas") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO CÓDIGO INFORMADO NÃO PERTENCE HÁ UM CAMINHÃO.\n");
					return;

				}
				if (caminhao instanceof Caminhao) {
					caminhao.imprimeVeiculo();
					flag = true;
				}

			}
		} catch (SQLException ex) {
			System.out.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nNÃO HÁ CAMINHÃO CADASTRADO COM O CÓDIGO " + codigo);
			System.out.print("\n");
		}

	}

}