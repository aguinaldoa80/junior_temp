package models;

public class Onibus extends Veiculo {
	private int capacidadePassageiros;

	public Onibus() {

	}

	public Onibus(int codigo, String marca, String modelo, int anoFabricacao, int anoModelo, String chassi,
			String placa, int capacidadePassageiros) {
		super(codigo, marca, modelo, anoFabricacao, anoModelo, chassi, placa);
		this.capacidadePassageiros = capacidadePassageiros;
	}

	public int getCapacidadePassageiros() {
		return capacidadePassageiros;
	}

	public void setCapacidadePassageiros(int capacidadePassageiros) {
		this.capacidadePassageiros = capacidadePassageiros;
	}

	public void imprimeVeiculo() {
		super.imprimeVeiculo();
		System.out.println("CAPACIDADE DE PASSAGEIROS: " + this.capacidadePassageiros);
		System.out.println("____________________________________");
	}
}
