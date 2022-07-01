package br.com.transmaximo.controller;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.transmaximo.controller.service.CaminhaoService;
import br.com.transmaximo.model.Caminhao;

@RequestMapping("/caminhoes")
@RestController
public class CaminhaoController {

	@Autowired
	private CaminhaoService caminhaoService;
	
	public ResponseEntity<Caminhao> cadastrar(@RequestBody Caminhao caminhao, UriComponentsBuilder uriBuilder) throws SQLException{
		
		caminhaoService.salvar(caminhao);
		
		URI uri = uriBuilder.path("caminhoes/{id}").buildAndExpand(caminhao.getId()).toUri();
		
		return ResponseEntity.created(uri).body(caminhao);
		
	}
}
