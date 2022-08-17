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

import br.com.transmaximo.controller.service.DocumentoService;
import br.com.transmaximo.model.Documento;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

@RequestMapping("/documentos")
@RestController
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;

	@Transactional
	@PostMapping
	public ResponseEntity<Documento> cadastrar(@RequestBody Documento documento, UriComponentsBuilder uriBuilder)
			throws SQLException {
		Documento documentoSalvo = documentoService.salvar(documento);

		URI uri = uriBuilder.path("documentos/{id}").buildAndExpand(documentoSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(documentoSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> buscarPorId(@PathVariable Long id) throws SQLException {
		Documento documento = documentoService.buscarPorId(id).get();

		return ResponseEntity.ok(documento);
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizar(@PathVariable Long id, @RequestBody Documento documentoAtualizado)
			throws SQLException {
		
		documentoService.atualizar(documentoAtualizado, id);
		Documento documento = documentoService.buscarPorId(id).get();

		return ResponseEntity.ok(documento);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Documento> deletar(@PathVariable Long id) throws SQLException {
		documentoService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/buscarTodos")
	public ResponseEntity<Pagina<Documento>> listarTodos(@RequestParam int tamanho, @RequestParam int numeroDaPagina){
		ConfigPagina configPagina = new ConfigPagina(tamanho, numeroDaPagina);
		Pagina<Documento> documento = documentoService.buscarTodos(configPagina);
		return ResponseEntity.ok(documento);
	}
}
