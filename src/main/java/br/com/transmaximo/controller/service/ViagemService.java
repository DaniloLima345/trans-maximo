package br.com.transmaximo.controller.service;

import java.util.Optional;

import br.com.transmaximo.model.Viagem;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

public interface ViagemService {

	Viagem salvar(Viagem viagem);
	
	Optional<Viagem> buscarPorId(Long id);
	
	void atualizar (Viagem viagem, Long id);
	
	void deletar(Long id);
	
	Pagina<Viagem> listarTodos(ConfigPagina configPagina);
}
