package br.com.transmaximo.controller.service;

import java.sql.SQLException;
import java.util.Optional;

import br.com.transmaximo.model.Documento;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

public interface DocumentoService {

	Documento salvar(Documento documento) throws SQLException;

	Optional<Documento> buscarPorId(Long id);
	
	void atualizar(Documento documento, Long id) throws SQLException;
	
	void deletar(Long id) throws SQLException;
	
	Pagina<Documento> buscarTodos(ConfigPagina configPagina);
}
