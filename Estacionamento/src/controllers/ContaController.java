package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estacionamento.com.conexao.Conexao;
import models.Cliente;
import models.Conta;
import models.Patio;
import models.Veiculo;

public class ContaController {
	
	private Integer codigo;
	private Integer diarias;
	private Integer mes;
	private Integer ano;
	private Conta conta;
	private Boolean pago;
	private Veiculo veiculo;
	private Cliente cliente;
	private Patio patio;
	
	public void cadastrarConta(Integer diarias, Integer mes, Integer ano, Cliente cliente, Veiculo veic, Patio patio){
		String sql = "";
		Connection con = null;
		PreparedStatement stm = null;
		ArrayList<Conta> contas = new ArrayList<Conta>();
		ResultSet rs = null;
		try {
			sql = "select * from contas where diarias = ? and mes = ? and ano = ? "
					+ "and clientes_codigo = ? and patios_codigo = ? and veiculos_codigo = ?";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			stm.setInt(1, diarias);
			stm.setInt(2, mes);
			stm.setInt(3, ano);
			stm.setInt(4, cliente.getCodigo());// aqui pode passar tbm so o codigo do cliente ao invés do cliente todo.
			stm.setInt(5, patio.getCodigo());
			stm.setInt(6, veic.getCodigo());
			
			rs = stm.executeQuery();

			if(rs.next()){ // se a conta já existe.
				System.err.println("Duplicidade de conta, se fudeo hahahah");
			}else{ // se não nois insere no banco.
				try {
					sql = "insert into contas (diarias, mes, ano, paga, clientes_codigo, patios_codigo, veiculos_codigo)"
							+ "values (?,?,?,?,?,?,?)";
					con = Conexao.getConexao();
					stm = con.prepareStatement(sql);
					stm.setInt(1, diarias);
					stm.setInt(2, mes);
					stm.setInt(3, ano);
					stm.setInt(4, cliente.getCodigo());// aqui pode passar tbm so o codigo do cliente ao invés do cliente todo.
					stm.setInt(5, patio.getCodigo());
					stm.setInt(6, veic.getCodigo());
					stm.execute();
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("by: Aguinaldo :)\nConta cadastrada com sucesso!!!");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Conta> listContas(){
		String sql = "";
		Connection con = null;
		PreparedStatement stm = null;
		ArrayList<Conta> contas = new ArrayList<Conta>();
		ResultSet rs = null;
		try {
			sql = "SELECT clientes.codigo as cod_cliente, clientes.nome as nome_cliente, "
					+ "clientes.logradouro as logradouro_cliente, clientes.numero as numero_cliente, "
					+ "clientes.bairro as bairro_cliente, clientes.municipio as municipio_cliente, "
					+ "clientes.estado as estado_cliente, clientes.cep as cep_cliente, "
					+ "clientes.telefone as telefone_cliente, patios.codigo as codigo_patio, "
					+ "patios.nome as nome_patio, patios.logradouro as logradouro_patio, "
					+ "patios.numero as numero_patio, patios.bairro as bairro_patio, "
					+ "patios.municipio as municipio_patio, patios.estado as estado_patio,"
					+ "patios.cep as cep_patio, patios.capacidadedeveiculos, patios.valordadiaria, "
					+ "patios.lotacao, veiculos.codigo as codigo_veiculo, veiculos.marca, "
					+ "veiculos.modelo, veiculos.anofabricacao, veiculos.anomodelo, veiculos.chassi, "
					+ "veiculos.placa, veiculos.capacidadecarga, veiculos.cilindradas, "
					+ "veiculos.capacidadepassageiros, veiculos.quantidadeportas, contas.codigo "
					+ "as codigo_conta, clientes_codigo, veiculos_codigo, patios_codigo, "
					+ "contas.diarias as diarias_conta, contas.mes, contas.ano, contas.paga "
					+ "FROM clientes, patios, veiculos,contas WHERE clientes.codigo = "
					+ "contas.clientes_codigo and patios.codigo = contas.patios_codigo and veiculos.codigo = "
					+ "contas.veiculos_codigo;";
			con = Conexao.getConexao();
			stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				Cliente cli = new Cliente();
				Patio pat = new Patio();
				Veiculo veic = new Veiculo();
				Conta conta = new Conta();
				try {
					cli.setNome(rs.getString("nome_cliente"));
					cli.setBairro(rs.getString("bairro_cliente"));
					cli.setCep(rs.getString("cep_cliente"));
					cli.setCodigo(rs.getInt("cod_cliente"));
					cli.setEstado(rs.getString("estado_cliente"));
					cli.setLogradouro(rs.getString("logradouro_cliente"));
					cli.setMunicipio(rs.getString("municipio_cliente"));
					cli.setNumero(rs.getString("numero_cliente"));
					cli.setTelefone(rs.getString("telefone_cliente"));
					pat.setCodigo(rs.getInt("codigo_patio"));
					pat.setNome(rs.getString("nome_patio"));
					pat.setLogradouro(rs.getString("logradouro_patio"));
					pat.setBairro(rs.getString("bairro_patio"));
					pat.setCep(rs.getString("cep_patio"));
					pat.setMunicipio(rs.getString("municipio_patio"));
					pat.setEstado(rs.getString("estado_patio"));
					pat.setCapacidadeDeVeiculos(rs.getInt("capacidadedeveiculos"));
					pat.setValorDaDiaria(rs.getFloat("valordadiaria"));
					pat.setLotacao(rs.getInt("lotacao"));
					veic.setCodigo(rs.getInt("codigo_veiculo"));
					veic.setMarca(rs.getString("marca"));
					veic.setAnoFabricacao(rs.getInt("anofabricacao"));
					veic.setAnoModelo(rs.getInt("anomodelo"));
					veic.setChassi(rs.getString("chassi"));
					veic.setModelo(rs.getString("modelo"));
					
					/*
					 * faz um métdo na classe veiculo pra descobrir qual veículo é moto,
					 * caminhão, carro não precisa fazer isso aqui.
					 * faz com query buscando em moto, se achar o id lá é moto se não,
					 * pesquisa em carro se achar a id é se não
					 * pesquisa em caminhão, assim por diante.
					 */
					conta.setCliente(cli);
					conta.setPatio(pat);
					conta.setVeiculo(veic);
					conta.setCodigo(rs.getInt("conta_codigo"));
					conta.setAno(rs.getInt("ano"));
					conta.setMes(rs.getInt("mes"));
					conta.setDiarias(rs.getInt("diarias_conta"));
					conta.setPaga(rs.getBoolean("paga"));
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				contas.add(conta);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return contas;
		
	}

}
