package br.com.transmaximo.controller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transmaximo.controller.service.ViagemService;
import br.com.transmaximo.dao.ViagemDAO;
import br.com.transmaximo.model.Viagem;

@Component
public class ViagemServiceImpl implements ViagemService {

	@Autowired
	private ViagemDAO viagemDAO;

	@Override
	public Viagem salvar(Viagem viagem) {
		return viagemDAO.salvar(viagem);
	}

}
