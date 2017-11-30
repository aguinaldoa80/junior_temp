package models;

public class Patio {
	private int codigo;
	private String nome;
	private String logradouro;
	private String numero;
	private String bairro;
	private String municipio;
	private String estado;
	private String cep;
	private int capacidadeDeVeiculos;
	private float valorDaDiaria;
	private int lotacao;

	public Patio() {

	}

	public Patio(int codigo, String nome, String logradouro, String numero, String bairro, String municipio,
			String estado, String cep, int capacidadeDeVeiculos, float valorDaDiaria, int lotacao) {
		this.codigo = codigo;
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.cep = cep;
		this.capacidadeDeVeiculos = capacidadeDeVeiculos;
		this.valorDaDiaria = valorDaDiaria;
		this.lotacao = lotacao;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public float getValorDaDiaria() {
		return valorDaDiaria;
	}

	public void setValorDaDiaria(float valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
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

	public int getCapacidadeDeVeiculos() {
		return capacidadeDeVeiculos;
	}

	public void setCapacidadeDeVeiculos(int capacidadeDeVeiculos) {
		this.capacidadeDeVeiculos = capacidadeDeVeiculos;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public void imprimePatio() {
		System.out.println("--== [ INFORMAÇÕES DO PÁTIO ] ==--\n");
		System.out.println("CÓDIGO: " + codigo);
		System.out.println("NOME: " + nome);
		System.out.println("LOGRADOURO: " + logradouro);
		System.out.println("NÚMERO: " + numero);
		System.out.println("BAIRRO: " + bairro);
		System.out.println("MUNICÍPIO: " + municipio);
		System.out.println("ESTADO: " + estado);
		System.out.println("CEP: " + cep);
		System.out.println("CAPACIDADE DE VEÍCULOS: " + capacidadeDeVeiculos);
		System.out.println("VALOR DA DIÁRIA: " + valorDaDiaria);
		System.out.println("LOTAÇÃO: " + lotacao);
		System.out.println("___________________________________\n");
	}
}
