package br.com.transmaximo.controller.service.impl;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.MotoristaService;
import br.com.transmaximo.dao.MotoristaDAO;
import br.com.transmaximo.model.Motorista;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@Component
public class MotoristaServiceImpl implements MotoristaService {

	@Autowired
	private MotoristaDAO motoristaDao;

	@Override
	public Motorista salvar(Motorista motorista) throws SQLException {
		return motoristaDao.salvar(motorista);
	}

	@Override
	public Optional<Motorista> buscarPorId(Long id) {
		return motoristaDao.buscarPorId(id);
	}

	@Override
	public Motorista buscarPorNome(String nome) {
		return motoristaDao.buscarPorNome(nome);
	}

	@Override
	public void atualizar(Motorista motorista, Long id) {
		motoristaDao.atualizar(motorista, id);
	}

	@Override
	public void deletar(Long id) {
		motoristaDao.deletar(id);
	}

	@Override
	public Pagina<Motorista> buscarTodos(ConfigPagina configPagina) {
		Pagina<Motorista> pagina = new Pagina<Motorista>();
		pagina.setConfig(configPagina);
		pagina.setPayload(motoristaDao.listarTodos(configPagina));
		return pagina;
	}

}
