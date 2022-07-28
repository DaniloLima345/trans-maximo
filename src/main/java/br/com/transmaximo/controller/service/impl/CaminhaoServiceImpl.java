package br.com.transmaximo.controller.service.impl;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.CaminhaoService;
import br.com.transmaximo.dao.CaminhaoDAO;
import br.com.transmaximo.model.Caminhao;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@Component
public class CaminhaoServiceImpl implements CaminhaoService {

	@Autowired
	private CaminhaoDAO caminhaoDao;

	@Override
	public Caminhao salvar(Caminhao caminhao) throws SQLException {
		return caminhaoDao.salvar(caminhao);

	}

	@Override
	public Optional<Caminhao> buscarPorId(Long id) {
		return caminhaoDao.buscarPorId(id);
	}

	@Override
	public Caminhao buscarPorPlaca(String placa) {
		return caminhaoDao.buscarPorPlaca(placa);
	}

	@Override
	public void atualizar(Caminhao caminhao, Long id) {
		caminhaoDao.atualizar(caminhao, id);
	}

	@Override
	public void deletar(Long id) {
		caminhaoDao.deletar(id);
		
	}

	@Override
	public Pagina<Caminhao> buscarTodos(ConfigPagina configPagina) {
		Pagina<Caminhao> pagina = new Pagina<Caminhao>();
		pagina.setConfig(configPagina);
		pagina.setPayload(caminhaoDao.listarTodos(configPagina));
		return pagina;
	}

}
