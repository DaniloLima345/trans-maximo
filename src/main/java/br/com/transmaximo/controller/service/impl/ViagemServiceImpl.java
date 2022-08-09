package br.com.transmaximo.controller.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.ViagemService;
import br.com.transmaximo.dao.ViagemDAO;
import br.com.transmaximo.model.Viagem;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@Component
public class ViagemServiceImpl implements ViagemService {

	@Autowired
	private ViagemDAO viagemDAO;

	@Override
	public Viagem salvar(Viagem viagem) {
		return viagemDAO.salvar(viagem);
	}

	@Override
	public Optional<Viagem> buscarPorId(Long id) {
		return viagemDAO.buscarPorId(id);
	}

	@Override
	public void atualizar(Viagem viagem, Long id) {
		viagemDAO.atualizar(viagem, id);
	}

	@Override
	public void deletar(Long id) {
		viagemDAO.deletar(id);
	}

	@Override
	public Pagina<Viagem> listarTodos(ConfigPagina configPagina) {
		Pagina<Viagem> pagina = new Pagina<Viagem>();
		pagina.setConfig(configPagina);
		pagina.setPayload(viagemDAO.listarTodos(configPagina));
		return pagina;
	}

}
