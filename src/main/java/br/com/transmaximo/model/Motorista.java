package br.com.transmaximo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Motorista {

	@JsonIgnore
	private Long id;
	private Long documento;
	private String nome;
	private String endereco;
	private String dataNascimento;

	public Motorista(Long id, Long documento, String nome, String endereco, String dataNascimento) {
		this.id = id;
		this.documento = documento;
		this.nome = nome;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
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

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
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
}
