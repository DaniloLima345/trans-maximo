package br.com.transmaximo.controller;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.transmaximo.controller.service.MotoristaService;
import br.com.transmaximo.model.Motorista;

@RequestMapping("/motoristas")
@RestController
public class MotoristaController {

	@Autowired
	private MotoristaService motoristaService;

	@Transactional
	@PostMapping
	public ResponseEntity<Motorista> cadastrar(@RequestBody Motorista motorista, UriComponentsBuilder uriBuilder)
			throws SQLException {
		motoristaService.salvar(motorista);

		URI uri = uriBuilder.path("motoristas/{id}").buildAndExpand(motorista.getId()).toUri();

		return ResponseEntity.created(uri).body(motorista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Motorista> buscarPorId(@PathVariable Long id) throws SQLException {
		Motorista motorista = motoristaService.buscarPorId(id);

		return ResponseEntity.ok(motorista);
	}

	@GetMapping("/buscarPorNome/{nome}")
	public ResponseEntity<Motorista> buscarPorNome(@PathVariable String nome) throws SQLException {
		Motorista motorista = motoristaService.buscarPorNome(nome);

		return ResponseEntity.ok(motorista);
	}
}
