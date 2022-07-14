package br.com.transmaximo.controller.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.MotoristaService;
import br.com.transmaximo.dao.MotoristaDAO;
import br.com.transmaximo.model.Motorista;

@Component
public class MotoristaServiceImpl implements MotoristaService {

	@Autowired
	private MotoristaDAO motoristaDao;

	@Override
	public void salvar(Motorista motorista) throws SQLException {
		motoristaDao.salvar(motorista);
	}

	@Override
	public Motorista buscarPorId(Long id) {
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

}
