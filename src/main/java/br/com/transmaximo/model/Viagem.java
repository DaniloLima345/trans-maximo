package br.com.transmaximo.model;

public class Viagem {

	private Long id;
	private String destino;
	private String tipoCarga;
	private Motorista motorista;
	private Caminhao caminhao;
	private StatusViagem statusViagem;

	public Viagem() {

	}

	public Viagem(Long id, String destino, String carga, Motorista motorista, Caminhao caminhao) {
		this.id = id;
		this.destino = destino;
		this.tipoCarga = carga;
		this.motorista = motorista;
		this.caminhao = caminhao;
		this.statusViagem = StatusViagem.AGENDADA;
	}

	public StatusViagem getStatusViagem() {
		return statusViagem;
	}

	public void setStatusViagem(StatusViagem statusViagem) {
		this.statusViagem = statusViagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Caminhao getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(Caminhao caminhao) {
		this.caminhao = caminhao;
	}

}
