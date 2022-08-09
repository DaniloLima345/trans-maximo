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

import br.com.transmaximo.controller.service.ViagemService;
import br.com.transmaximo.model.Viagem;
import br.com.transmaximo.paginacao.ConfigPagina;
import br.com.transmaximo.paginacao.Pagina;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<Viagem> buscarPorId (@PathVariable Long id) throws SQLException{
		Viagem viagem = viagemService.buscarPorId(id).get();
		
		return ResponseEntity.ok(viagem);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Viagem> atualizar (@PathVariable Long id, @RequestBody Viagem viagemAtualizada) throws SQLException {
		viagemService.atualizar(viagemAtualizada, id);
		Viagem viagem = viagemService.buscarPorId(id).get();
		
		return ResponseEntity.ok(viagem);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Viagem> deletar (@PathVariable Long id) throws SQLException {
		viagemService.deletar(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/buscarTodos")
	public ResponseEntity<Pagina<Viagem>> listarTodos (@RequestParam int tamanho, @RequestParam int numeroDaPagina) throws SQLException {
		ConfigPagina configPagina = new ConfigPagina(tamanho, numeroDaPagina);
		Pagina<Viagem> viagens = viagemService.listarTodos(configPagina);
		return ResponseEntity.ok(viagens);
	}
}
