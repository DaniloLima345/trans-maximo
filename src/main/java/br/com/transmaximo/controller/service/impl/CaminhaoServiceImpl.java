package br.com.transmaximo.controller.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.CaminhaoService;
import br.com.transmaximo.dao.CaminhaoDAO;
import br.com.transmaximo.model.Caminhao;

@Component
public class CaminhaoServiceImpl implements CaminhaoService {

	@Autowired
	private CaminhaoDAO caminhaoDao;
	
	@Override
	public void salvar(Caminhao caminhao) throws SQLException {
		caminhaoDao.salvar(caminhao);
		
	}

	@Override
	public Caminhao buscarPorId(Long id) {
		return caminhaoDao.buscarPorId(id);
	}
	
	@Override
	public Caminhao buscarPorPlaca(String placa) {
		return caminhaoDao.buscarPorPlaca(placa);
	}

}
