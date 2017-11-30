package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Carro;
import models.Veiculo;

public class CarroDAO {
	private Carro carro;
	Scanner sc;
	String sql;
	Connection con;
	PreparedStatement stm;

	public CarroDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
	}

	public void adicionaCarro() {
		carro = new Carro();
		int anoF = 0, anoM = 0, qtdportas = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CADASTRO DE CARRO ] ==--\n");
		System.out.println("MARCA: ");
		carro.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		carro.setModelo(sc.nextLine());
		do {
			System.out.println("ANO DA FABRICA��O: ");
			str = sc.nextLine();
			try {
				anoF = Integer.parseInt(str);
				flag = true;
				if (anoF < 1885) {
					System.out.println("\nO ANO DE FABRICA��O DIGITADO N�O � V�LIDO.");
					System.out.println("O ANO DEVE IGUAL OU SUPERIOR A 1885.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		carro.setAnoFabricacao(anoF);
		do {
			flag = false;
			System.out.println("ANO DO MODELO: ");
			str = sc.nextLine();
			try {
				anoM = Integer.parseInt(str);
				flag = true;
				if (anoM < anoF) {
					System.out.println("\nO ANO DO MODELO N�O � V�LIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR AO ANO DE FABRICA��O.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		carro.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		carro.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		carro.setPlaca(sc.nextLine());

		do {
			System.out.println("QUANTIDADE DE PORTAS: ");
			str = sc.nextLine();
			try {
				qtdportas = Integer.parseInt(str);
				flag = true;
				if (qtdportas < 1) {
					System.out.println("\nA QUANTIDADE DE PORTAS DEVE SER IGUAL OU MAIOR QUE UMA.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		carro.setQtdPortas(qtdportas);
		try {
			sql = "insert into veiculos (marca, modelo, anofabricacao, anomodelo, chassi, placa, quantidadeportas) "
					+ "values (?,?,?,?,?,?,?)";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setString(1, carro.getMarca());
			stm.setString(2, carro.getModelo());
			stm.setInt(3, carro.getAnoFabricacao());
			stm.setInt(4, carro.getAnoModelo());
			stm.setString(5, carro.getChassi());
			stm.setString(6, carro.getPlaca());
			stm.setInt(7, carro.getQtdPortas());
			stm.execute();
			stm.close();
			con.close();
			System.out.println("\nTRANSA��O DE CADASTRO REALIZADA COM SUCESSO.\n");
		} catch (Exception e) {

			System.err.println(e);
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n");
		}

	}

	public void alteraCarro() {
		int codigo = 0, anoF = 0, anoM = 0, qtdportas = 0;
		String str;
		boolean flag = false;
		System.out.println("--== [ ALTERA��O DE CARRO ] ==--");
		System.out.println("\nINFORME O C�DIGO DO CARRO.\n");
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
			sql = "select * from veiculos where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				carro = new Carro(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("quantidadeportas"));

				if (rs.getFloat("capacidadecarga") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO C�DIGO INFORMADO N�O PERTENCE H� UM CARRO\n");
					return;
				}
				carro.imprimeVeiculo();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nN�O H� CARRO CADASTRADO COM O C�DIGO " + codigo);
			System.out.print("\n");
			return;
		}

		System.out.println("\n--== [ ALTERAR INFORMA��ES CARRO ] ==--\n");
		System.out.println("MARCA: ");
		carro.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		carro.setModelo(sc.nextLine());
		do {
			System.out.println("ANO DA FABRICA��O: ");
			str = sc.nextLine();
			try {
				anoF = Integer.parseInt(str);
				flag = true;
				if (anoF < 1930) {
					System.out.println("\nO ANO DE FABRICA��O DIGITADO N�O � V�LIDO.");
					System.out.println("O ANO DEVE SER SUPERIOR A 1930.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		carro.setAnoFabricacao(anoF);
		do {
			flag = false;
			System.out.println("ANO DO MODELO: ");
			str = sc.nextLine();
			try {
				anoM = Integer.parseInt(str);
				flag = true;
				if (anoM < anoF) {
					System.out.println("\nO ANO DO MODELO N�O � V�LIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR AO ANO DE FABRICA��O.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		carro.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		carro.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		carro.setPlaca(sc.nextLine());
		do {
			System.out.println("QUANTIDADE DE PORTAS: ");
			str = sc.nextLine();
			try {
				qtdportas = Integer.parseInt(str);
				flag = true;
				if (qtdportas < 1) {
					System.out.println("\nA QUANTIDADE DE PORTAS DEVE SER MAIOR QUE UMA.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		carro.setQtdPortas(qtdportas);

		try {
			sql = "update veiculos set marca = \'" + carro.getMarca() + "\', modelo = \'" + carro.getModelo()
					+ "\', anofabricacao = \'" + carro.getAnoFabricacao() + "\', anomodelo = \'" + carro.getAnoModelo()
					+ "\', placa = \'" + carro.getPlaca() + "\', chassi = \'" + carro.getChassi()
					+ "\', quantidadeportas = \'" + carro.getQtdPortas() + "\' where codigo = " + carro.getCodigo();
			stm = con.prepareStatement(sql);
			stm.execute();
			System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
		} catch (SQLException e) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
		}

	}

	public void removeCarro() {
		int codigo = 0, resp = 0;
		String str;
		boolean flag = false;

		System.out.println("\n--== [ EXCLUS�O DE CARRO ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DO CARRO.\n");
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
			sql = "select * from veiculos where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				carro = new Carro(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("quantidadeportas"));

				if (rs.getFloat("capacidadecarga") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO C�DIGO INFORMADO N�O PERTENCE H� UM CARRO.\n");
					return;
				}
				carro.imprimeVeiculo();
				flag = true;

			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}
		if (!flag) {
			System.out.println("\nN�O H� CARRO CADASTRADO COM O C�DIGO " + codigo);
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
				sql = "delete from veiculos where codigo = " + codigo;
				stm = con.prepareStatement(sql);
				stm.execute();
				System.out.println("\nTRANSA��O DE EXCLUS�O REALIZADA COM SUCESSO.\n");
				flag = true;
			} catch (SQLException ex) {
				System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
			}

		} else {
			System.out.println("\nTRANSA��O DE EXCLUS�O N�O REALIZADA.\n");
			flag = true;
		}
	}

	public void relatorioCarro() {
		System.out.println("--== [ RELAT�RIO ] ==--");
		ArrayList<Veiculo> carros = new ArrayList<Veiculo>();
		ResultSet rs = null;
		try {
			String sql = "select * from veiculos";
			con = Conexao.getConexao();
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Carro car = new Carro();
				try {
					car.setCodigo(rs.getInt("codigo"));
					car.setMarca(rs.getString("marca"));
					car.setModelo(rs.getString("modelo"));
					car.setAnoFabricacao(rs.getInt("anofabricacao"));
					car.setAnoModelo(rs.getInt("anomodelo"));
					car.setChassi(rs.getString("chassi"));
					car.setPlaca(rs.getString("placa"));
					car.setQtdPortas(rs.getInt("quantidadeportas"));

				} catch (Exception e) {

				}
				if (rs.getInt("quantidadeportas") > 0) {
					carros.add(car);
				}
			}
		} catch (Exception e) {

		}

		try {
			for (Veiculo car : carros) {
				if (car instanceof Carro) {
					car.imprimeVeiculo();
				}

			}
			System.out.println("\nTOTAL DE CARROS CADASTRADOS " + carros.size());

		} catch (Exception e) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n");
		}
	}

	public void consultaCarro() {
		int codigo = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CONSULTAR CARRO ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DO CARRO.\n");
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
			sql = "select * from veiculos where codigo = " + codigo;
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				carro = new Carro(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("quantidadeportas"));

				if (rs.getInt("capacidadecarga") > 0 || rs.getInt("cilindradas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO C�DIGO INFORMADO N�O PERTENCE H� UM CARRO.\n");
					return;

				}
				if (carro instanceof Carro) {
					carro.imprimeVeiculo();
					flag = true;
				}

			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nN�O H� CARRO CADASTRADO COM O C�DIGO " + codigo);
			System.out.print("\n");
		}

	}

}
