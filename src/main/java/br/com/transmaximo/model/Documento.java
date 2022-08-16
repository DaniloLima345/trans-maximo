package br.com.transmaximo.model;

public class Documento {
	
	private Long id;
	private String tipoDocumento;
	private String dataVencimento;
	private Motorista motorista;

	public Documento() {
		// TODO Auto-generated constructor stub
	}

	public Documento(Long id, String tipoDocumento, String dataVencimento, Motorista motorista) {
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.dataVencimento = dataVencimento;
		this.motorista = motorista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

}
