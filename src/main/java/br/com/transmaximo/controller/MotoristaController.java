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

import br.com.transmaximo.controller.service.MotoristaService;
import br.com.transmaximo.model.Motorista;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@RequestMapping("/motoristas")
@RestController // Avisando o spring que ele vai receber requisiçoes do tipo rest
public class MotoristaController {

	@Autowired
	private MotoristaService motoristaService;

	@Transactional // Ele vai utilizar uma transação do banco de dados
	@PostMapping
	public ResponseEntity<Motorista> cadastrar(@RequestBody Motorista motorista, UriComponentsBuilder uriBuilder)
			throws SQLException {
		Motorista motoristaSalvo = motoristaService.salvar(motorista);

		URI uri = uriBuilder.path("motoristas/{id}").buildAndExpand(motoristaSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(motoristaSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Motorista> buscarPorId(@PathVariable Long id) throws SQLException {
		Motorista motorista = motoristaService.buscarPorId(id).get();

		return ResponseEntity.ok(motorista);
	}

	@GetMapping("/buscarPorNome/{nome}")
	public ResponseEntity<Motorista> buscarPorNome(@PathVariable String nome) throws SQLException {
		Motorista motorista = motoristaService.buscarPorNome(nome);

		return ResponseEntity.ok(motorista);
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Motorista> atualizar(@PathVariable Long id, @RequestBody Motorista motoristaAtualizado)
			throws SQLException {
		motoristaService.atualizar(motoristaAtualizado, id);
		Motorista motorista = motoristaService.buscarPorId(id).get();

		return ResponseEntity.ok(motorista);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Motorista> deletar(@PathVariable Long id) throws SQLException {
		motoristaService.deletar(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/buscarTodos")
	public ResponseEntity<Pagina<Motorista>> buscarTodos(@RequestParam int tamanho, @RequestParam int numeroDaPagina) {
		ConfigPagina configPagina = new ConfigPagina(tamanho, numeroDaPagina);
		Pagina<Motorista> motoristas = motoristaService.buscarTodos(configPagina);
		return ResponseEntity.ok(motoristas);
	}
}
