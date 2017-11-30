package models;

public class Caminhao extends Veiculo {
	private float capacidadeCarga;

	public Caminhao() {

	}

	public Caminhao(int codigo, String marca, String modelo, int anoFabricacao, int anoModelo, String chassi,
			String placa, float capacidadeCarga) {
		super(codigo, marca, modelo, anoFabricacao, anoModelo, chassi, placa);
		this.capacidadeCarga = capacidadeCarga;

	}

	public float getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public void setCapacidadeCarga(float capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}

	public void imprimeVeiculo() {
		super.imprimeVeiculo();
		System.out.println("CAPACIDADE DE CARGA: " + this.capacidadeCarga);
		System.out.println("____________________________________");
	}
}