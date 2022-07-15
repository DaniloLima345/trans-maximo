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

import br.com.transmaximo.controller.service.DocumentoService;
import br.com.transmaximo.model.Documento;

@RequestMapping("/documentos")
@RestController
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;

	@Transactional
	@PostMapping
	public ResponseEntity<Documento> cadastrar(@RequestBody Documento documento, UriComponentsBuilder uriBuilder)
			throws SQLException {
		documentoService.salvar(documento);

		URI uri = uriBuilder.path("documentos/{id}").buildAndExpand(documento.getId()).toUri();

		return ResponseEntity.created(uri).body(documento);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> buscarPorId(@PathVariable Long id) throws SQLException {
		Documento documento = documentoService.buscarPorId(id);

		return ResponseEntity.ok(documento);
	}
}
