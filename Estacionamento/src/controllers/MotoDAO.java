package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Moto;
import models.Veiculo;

public class MotoDAO {

	private Moto moto;
	Scanner sc;
	String sql;
	Connection con;
	PreparedStatement stm;

	public MotoDAO() {
		sc = new Scanner(System.in);
		sql = "";
		con = null;
		stm = null;
	}

	public void adicionaMoto() {
		moto = new Moto();
		int anoF = 0, anoM = 0, cilin = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CADASTRO DE MOTO ] ==--\n");
		System.out.println("MARCA: ");
		moto.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		moto.setModelo(sc.nextLine());
		do {
			System.out.println("ANO DA FABRICA��O: ");
			str = sc.nextLine();
			try {
				anoF = Integer.parseInt(str);
				flag = true;
				if (anoF < 1869) {
					System.out.println("\nO ANO DE FABRICA��O DIGITADO N�O � V�LIDO.");
					System.out.println("O ANO DEVE SER IGUAL OU SUPERIOR A 1869.\n");
					flag = false;

				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);
		moto.setAnoFabricacao(anoF);
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
		moto.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		moto.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		moto.setPlaca(sc.nextLine());
		do {
			System.out.println("CILINDRADAS: ");
			str = sc.nextLine();
			try {
				cilin = Integer.parseInt(str);
				flag = true;
				if (cilin <= 0) {
					System.out.println("\nA QUANTIDADE DE CILINDRADAS DEVE SER MAIOR 0.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		moto.setCilindradas(cilin);
		try {
			sql = "insert into veiculos (marca, modelo, anofabricacao, anomodelo, chassi, placa, cilindradas) "
					+ "values (?,?,?,?,?,?,?)";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setString(1, moto.getMarca());
			stm.setString(2, moto.getModelo());
			stm.setInt(3, moto.getAnoFabricacao());
			stm.setInt(4, moto.getAnoModelo());
			stm.setString(5, moto.getChassi());
			stm.setString(6, moto.getPlaca());
			stm.setInt(7, moto.getCilindradas());
			stm.execute();
			stm.close();
			con.close();
			System.out.println("\nTRANSA��O DE CADASTRO REALIZADA COM SUCESSO.\n");
		} catch (Exception e) {

			System.err.println(e);
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n");
		}

	}

	public void alteraMoto() {
		int codigo = 0, anoF = 0, anoM = 0, cilin = 0;
		String str;
		boolean flag = false;
		System.out.println("--== [ ALTERA��O DE MOTO ] ==--");
		System.out.println("\nINFORME O C�DIGO DA MOTO.\n");
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
				moto = new Moto(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("cilindradas"));

				if (rs.getFloat("capacidadecarga") > 0 || rs.getInt("quantidadeportas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO C�DIGO INFORMADO N�O PERTENCE H� UMA MOTO\n");
					return;
				}
				moto.imprimeVeiculo();
				flag = true;
			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nN�O H� MOTO CADASTRADA COM O C�DIGO " + codigo);
			System.out.print("\n");
			return;
		}

		System.out.println("\n--== [ ALTERAR INFORMA��ES MOTO] ==--\n");
		System.out.println("MARCA: ");
		moto.setMarca(sc.nextLine());
		System.out.println("MODELO: ");
		moto.setModelo(sc.nextLine());
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
		moto.setAnoFabricacao(anoF);
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
		moto.setAnoModelo(anoM);
		System.out.println("CHASSI: ");
		moto.setChassi(sc.nextLine());
		System.out.println("PLACA: ");
		moto.setPlaca(sc.nextLine());
		do {
			System.out.println("CILINDRADAS: ");
			str = sc.nextLine();
			try {
				cilin = Integer.parseInt(str);
				flag = true;
				if (cilin <= 0) {
					System.out.println("\nA QUANTIDADE DE CILINDRADAS DEVE SER MAIOR ZERO.\n");
					flag = false;
				}
			} catch (NumberFormatException e) {

				System.out.println("\nLETRAS N�O S�O PERMITIDAS.\n");
				flag = false;

			}
		} while (!flag);

		moto.setCilindradas(cilin);
		try {
			sql = "update veiculos set marca = \'" + moto.getMarca() + "\', modelo = \'" + moto.getModelo()
					+ "\', anofabricacao = \'" + moto.getAnoFabricacao() + "\', anomodelo = \'" + moto.getAnoModelo()
					+ "\', placa = \'" + moto.getPlaca() + "\', chassi = \'" + moto.getChassi() + "\', cilindradas = \'"
					+ moto.getCilindradas() + "\' where codigo = " + moto.getCodigo();
			stm = con.prepareStatement(sql);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
		}
		System.out.println("\nTRANSA��O DE ALTERA��O REALIZADA COM SUCESSO.\n");
	}

	public void removeMoto() {
		int codigo = 0, resp = 0;
		String str;
		boolean flag = false;

		System.out.println("\n--== [ EXCLUS�O DE MOTOS ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DA MOTO.\n");
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
				moto = new Moto(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("cilindradas"));

				if (rs.getFloat("capacidadecarga") > 0 || rs.getInt("quantidadeportas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO C�DIGO INFORMADO N�O PERTENCE H� UMA MOTO.\n");
					return;
				}
				moto.imprimeVeiculo();
				flag = true;

			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}
		if (!flag) {
			System.out.println("\nN�O H� MOTO CADASTRADA COM O C�DIGO " + codigo);
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
			System.out.println("\nTRANSA��O DE EXCLUS�O N�O REALIZADA\n");
			flag = true;
		}
	}

	public void relatorioMoto() {
		System.out.println("--== [ RELAT�RIO ] ==--");
		ArrayList<Veiculo> motos = new ArrayList<Veiculo>();
		ResultSet rs = null;
		try {
			String sql = "select * from veiculos";
			con = Conexao.getConexao();
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				Moto mot = new Moto();
				try {
					mot.setCodigo(rs.getInt("codigo"));
					mot.setMarca(rs.getString("marca"));
					mot.setModelo(rs.getString("modelo"));
					mot.setAnoFabricacao(rs.getInt("anofabricacao"));
					mot.setAnoModelo(rs.getInt("anomodelo"));
					mot.setChassi(rs.getString("chassi"));
					mot.setPlaca(rs.getString("placa"));
					mot.setCilindradas(rs.getInt("cilindradas"));

				} catch (Exception e) {

				}
				if (rs.getInt("cilindradas") > 0) {
					motos.add(mot);
				}
			}
		} catch (Exception e) {

		}

		try {
			for (Veiculo mot : motos) {
				if (mot instanceof Moto) {
					mot.imprimeVeiculo();
				}

			}
			System.out.println("\nTOTAL DE MOTOS CADASTRADAS " + motos.size());

		} catch (Exception e) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + e);
		}
	}

	public void consultaMoto() {
		int codigo = 0;
		boolean flag = false;
		String str;
		System.out.println("\n--== [ CONSULTAR MOTO ] ==--\n");
		System.out.println("\nINFORME O C�DIGO DA MOTO.\n");
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
				moto = new Moto(codigo, rs.getString("marca"), rs.getString("modelo"), rs.getInt("anofabricacao"),
						rs.getInt("anomodelo"), rs.getString("placa"), rs.getString("chassi"),
						rs.getInt("cilindradas"));

				if (rs.getInt("capacidadecarga") > 0 || rs.getInt("quantidadeportas") > 0
						|| rs.getInt("capacidadepassageiros") > 0) {
					System.out.println("\nO C�DIGO INFORMADO N�O PERTENCE H� UMA MOTO.\n");
					return;

				}
				if (moto instanceof Moto) {
					moto.imprimeVeiculo();
					flag = true;
				}

			}
		} catch (SQLException ex) {
			System.out.println("\nN�O FOI POSS�VEL ACESSAR A BASE DE DADOS.\n" + ex);
		}

		if (!flag) {
			System.out.println("\nN�O H� MOTO CADASTRADA COM O C�DIGO " + codigo);
			System.out.print("\n");
		}

	}

}
