package br.com.transmaximo.controller.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.DocumentoService;
import br.com.transmaximo.dao.DocumentoDAO;
import br.com.transmaximo.model.Documento;

@Component
public class DocumentoServiceImpl implements DocumentoService {

	@Autowired
	private DocumentoDAO documentoDAO;

	@Override
	public int salvar(Documento documento) throws SQLException {
		return documentoDAO.salvar(documento);
	}

	@Override
	public Documento buscarPorId(Long id) {
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

}
