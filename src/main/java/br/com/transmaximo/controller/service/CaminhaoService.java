package br.com.transmaximo.controller.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import br.com.transmaximo.model.Caminhao;

@Service
public interface CaminhaoService {

	void salvar(Caminhao caminhao) throws SQLException;

	Caminhao buscarPorId(Long id);

	Caminhao buscarPorPlaca(String placa);

	void atualizar(Caminhao caminhao, Long id);
	
	void deletar(Long id);
}
