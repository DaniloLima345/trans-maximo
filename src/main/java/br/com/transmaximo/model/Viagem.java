package br.com.transmaximo.model;

public class Viagem {

	private Long id;
	private String destino;
	private String tipoCarga;
	private Long idMotorista;
	private Long idCaminhao;
	private StatusViagem statusViagem;

	public Viagem() {

	}

	public Viagem(Long id, String destino, String carga, Long idMotorista, Long idCaminhao) {
		this.id = id;
		this.destino = destino;
		this.tipoCarga = carga;
		this.idMotorista = idMotorista;
		this.idCaminhao = idCaminhao;
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

	public Long getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Long idMotorista) {
		this.idMotorista = idMotorista;
	}

	public Long getIdCaminhao() {
		return idCaminhao;
	}

	public void setIdCaminhao(Long idCaminhao) {
		this.idCaminhao = idCaminhao;
	}

}
