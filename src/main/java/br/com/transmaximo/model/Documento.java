package br.com.transmaximo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Documento {

	@JsonIgnore
	private Long id;
	private String tipoDocumento;
	private String dataVencimento;
	private Long idMotorista;

	public Documento() {
		// TODO Auto-generated constructor stub
	}

	public Documento(Long id, String tipoDocumento, String dataVencimento, Long idMotorista) {
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.dataVencimento = dataVencimento;
		this.idMotorista = idMotorista;
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

	public Long getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Long idMotorista) {
		this.idMotorista = idMotorista;
	}

}
