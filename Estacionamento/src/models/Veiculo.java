package models;

public class Veiculo {
	private int codigo;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	private int anoModelo;
	private String chassi;
	private String placa;

	public Veiculo(int codigo, String marca, String modelo, int anoFabricacao, int anoModelo, String chassi,
			String placa) {
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.anoModelo = anoModelo;
		this.chassi = chassi;
		this.placa = placa;

	}

	public Veiculo() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void imprimeVeiculo() {
		System.out.println("\n--== [ INFORMAÇÕES DO VEÍCULO ] ==--\n");
		System.out.println("CÓDIGO: " + this.codigo);
		System.out.println("MARCA: " + this.marca);
		System.out.println("MODELO: " + this.modelo);
		System.out.println("ANO DE FABRICAÇÃO: " + this.anoFabricacao);
		System.out.println("ANO DO MODELO: " + this.anoModelo);
		System.out.println("CHASSI: " + this.chassi);
		System.out.println("PLACA: " + this.placa);

	}

}
