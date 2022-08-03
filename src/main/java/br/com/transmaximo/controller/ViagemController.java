package br.com.transmaximo.controller;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.transmaximo.controller.service.ViagemService;
import br.com.transmaximo.model.Viagem;

@RequestMapping("/viagens")
@RestController
public class ViagemController {

	@Autowired
	private ViagemService viagemService;

	@Transactional
	@PostMapping
	public ResponseEntity<Viagem> cadastrar(@RequestBody Viagem viagem, UriComponentsBuilder uriBuilder)
			throws SQLException {
		viagemService.salvar(viagem);
		
		URI uri = uriBuilder.path("viagens/{id}").buildAndExpand(viagem.getId()).toUri();
		
		return ResponseEntity.created(uri).body(viagem);
	}
}
