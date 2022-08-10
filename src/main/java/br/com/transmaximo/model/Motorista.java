package br.com.transmaximo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Motorista {

	@JsonIgnore
	private Long id;
	private String nome;
	private String endereco;
	private String dataNascimento;
	private Long idDocumento;

	public Motorista(Long id, String nome, String endereco, String dataNascimento, Long idDocumento) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.idDocumento = idDocumento;
	}

	public Motorista() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}
}
