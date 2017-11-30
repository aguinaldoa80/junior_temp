package models;

public class Moto extends Veiculo {
	private int cilindradas;

	public Moto() {

	}

	public Moto(int codigo, String marca, String modelo, int anoFabricacao, int anoModelo, String chassi, String placa,
			int cilindradas) {
		super(codigo, marca, modelo, anoFabricacao, anoModelo, chassi, placa);
		this.cilindradas = cilindradas;
	}

	public int getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(int cilindradas) {
		this.cilindradas = cilindradas;
	}

	public void imprimeVeiculo() {
		super.imprimeVeiculo();
		System.out.println("CILINDRADAS: " + this.cilindradas);
		System.out.println("____________________________________");
	}
}
