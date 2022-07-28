package br.com.transmaximo.controller.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.transmaximo.model.Caminhao;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@Service
public interface CaminhaoService {

	Caminhao salvar(Caminhao caminhao) throws SQLException;

	Optional<Caminhao> buscarPorId(Long id);

	Caminhao buscarPorPlaca(String placa);

	void atualizar(Caminhao caminhao, Long id);
	
	void deletar(Long id);
	
	Pagina<Caminhao> buscarTodos(ConfigPagina configPagina);
}
