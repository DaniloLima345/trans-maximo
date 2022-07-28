package br.com.transmaximo.controller;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.transmaximo.controller.service.CaminhaoService;
import br.com.transmaximo.model.Caminhao;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@RequestMapping("/caminhoes")
@RestController
public class CaminhaoController {

	@Autowired
	private CaminhaoService caminhaoService;

	@Transactional
	@PostMapping
	public ResponseEntity<Caminhao> cadastrar(@RequestBody Caminhao caminhao, UriComponentsBuilder uriBuilder)
			throws SQLException {

		caminhaoService.salvar(caminhao);

		URI uri = uriBuilder.path("caminhoes/{id}").buildAndExpand(caminhao.getId()).toUri();

		return ResponseEntity.created(uri).body(caminhao);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Caminhao> buscarPorId(@PathVariable Long id) {
		Caminhao caminhao = caminhaoService.buscarPorId(id).get();

		return ResponseEntity.ok(caminhao);
	}

	@GetMapping("/buscarPorPlaca/{placa}")
	public ResponseEntity<Caminhao> buscarPorPlaca(@PathVariable String placa) {
		Caminhao caminhao = caminhaoService.buscarPorPlaca(placa);

		return ResponseEntity.ok(caminhao);
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Caminhao> atualizar(@PathVariable Long id, @RequestBody Caminhao caminhaoAtualziado)
			throws SQLException {

		caminhaoService.atualizar(caminhaoAtualziado, id);
		Caminhao caminhao = caminhaoService.buscarPorId(id).get();

		return ResponseEntity.ok(caminhao);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) throws SQLException {
		caminhaoService.deletar(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/buscarTodos")
	public ResponseEntity<Pagina<Caminhao>> buscarTodos(@RequestParam int tamanho, @RequestParam int numeroDaPagina) {
		ConfigPagina configPagina = new ConfigPagina(tamanho, numeroDaPagina);
		Pagina<Caminhao> caminhoes = caminhaoService.buscarTodos(configPagina);
		return ResponseEntity.ok(caminhoes);
	}
}
