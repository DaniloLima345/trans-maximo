package br.com.transmaximo.controller.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.transmaximo.model.Motorista;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@Service
public interface MotoristaService {

	Motorista salvar(Motorista motorista) throws SQLException;

	Optional<Motorista> buscarPorId(Long id);

	Motorista buscarPorNome(String nome);

	void atualizar(Motorista motorista, Long id);
	
	void deletar(Long id);
	
	Pagina<Motorista> buscarTodos(ConfigPagina configPagina);
}
