package models;

public class Cliente {
	private int codigo;
	private String nome;
	private String logradouro;
	private String numero;
	private String bairro;
	private String municipio;
	private String estado;
	private String cep;
	private String telefone;

	public Cliente() {

	}

	public Cliente(int codigo, String nome, String logradouro, String numero, String bairro, String municipio,
			String estado, String cep, String telefone) {
		this.codigo = codigo;
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void imprimeCliente() {
		System.out.println("\n--== [ RELATÓRIO DE CLIENTES ] ==--\n");
		System.out.println("CÓDIGO: " + codigo);
		System.out.println("NOME: " + nome);
		System.out.println("LOGRADOURO: " + logradouro);
		System.out.println("NÚMERO: " + numero);
		System.out.println("BAIRRO: " + bairro);
		System.out.println("MUNICÍPIO: " + municipio);
		System.out.println("ESTADO: " + estado);
		System.out.println("CEP: " + cep);
		System.out.println("TELEFONE: " + telefone);
		System.out.println("___________________________________");
	}

}