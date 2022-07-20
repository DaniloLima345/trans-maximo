package br.com.transmaximo.controller.service;

import java.sql.SQLException;

import br.com.transmaximo.model.Documento;

public interface DocumentoService {

	int salvar(Documento documento) throws SQLException;

	Documento buscarPorId(Long id);
	
	void atualizar(Documento documento, Long id) throws SQLException;
	
	void deletar(Long id) throws SQLException;
}
