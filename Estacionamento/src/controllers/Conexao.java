package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Conexao {
	public static Connection getConexao() {

		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		try {
			Properties props = new Properties();
			props.put("user", "postgres");
			props.put("password", "123456");
			props.put("ENCODING", "UTF8");
			props.put("LC_COLLATE", "pt_BR.UTF-8");
			props.put("LC_CTYPE", "pt_BR.UTF-8");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/estacionamento", props);
		} catch (SQLException ex) {
			System.err.println("\nNÃO FOI POSSÍVEL ACESSAR A BASE DE DADOS.\n");
			System.exit(0);
		}
		System.err.println("\nCONECTADO COM SUCESSO.\n");
		return null;
	}

}