package br.com.transmaximo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Caminhao {

	private Long id;
	private String placa;
	private String modelo;
	private String anoFabricacao;
	private Double capacidade;

	public Caminhao(Long id, String placa, String modelo, String anoFabricacao, Double capacidade) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.capacidade = capacidade;
	}
	
	public Caminhao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}
}
