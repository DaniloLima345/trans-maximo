package br.com.transmaximo.controller.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import br.com.transmaximo.model.Motorista;

@Service
public interface MotoristaService {

	void salvar(Motorista motorista) throws SQLException;
}
