package models;

public class Carro extends Veiculo {
	private int qtdPortas;

	public Carro() {

	}

	public Carro(int codigo, String marca, String modelo, int anoFabricacao, int anoModelo, String chassi, String placa,
			int qtdPortas) {
		super(codigo, marca, modelo, anoFabricacao, anoModelo, chassi, placa);
		this.qtdPortas = qtdPortas;

	}

	public int getQtdPortas() {
		return qtdPortas;
	}

	public void setQtdPortas(int qtdPortas) {
		this.qtdPortas = qtdPortas;
	}

	public void imprimeVeiculo() {
		super.imprimeVeiculo();
		System.out.println("QUANTIDADE DE PORTAS: " + this.qtdPortas);
		System.out.println("____________________________________");
	}
}