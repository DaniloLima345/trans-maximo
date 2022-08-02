package br.com.transmaximo.controller.service.impl;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.DocumentoService;
import br.com.transmaximo.dao.DocumentoDAO;
import br.com.transmaximo.model.Documento;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@Component
public class DocumentoServiceImpl implements DocumentoService {

	@Autowired
	private DocumentoDAO documentoDAO;

	@Override
	public Documento salvar(Documento documento) throws SQLException {
		return documentoDAO.salvar(documento);
	}

	@Override
	public Optional<Documento> buscarPorId(Long id) {
		return documentoDAO.buscarPorId(id);
	}

	@Override
	public void atualizar(Documento documento, Long id) throws SQLException {
		documentoDAO.atualizar(documento, id);

	}

	@Override
	public void deletar(Long id) throws SQLException {
		documentoDAO.deletar(id);

	}

	@Override
	public Pagina<Documento> buscarTodos(ConfigPagina configPagina) {
		Pagina<Documento> pagina = new Pagina<Documento>();
		pagina.setConfig(configPagina);
		pagina.setPayload(documentoDAO.listarTodos(configPagina));
		return pagina;
	}

}
