package br.com.transmaximo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/caminhoes")
@RestController
public class CaminhaoController {
	
	@GetMapping
	public String teste() {
		return "teste";
	}

}
