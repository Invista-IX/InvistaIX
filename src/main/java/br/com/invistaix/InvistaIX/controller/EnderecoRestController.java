package br.com.invistaix.InvistaIX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invistaix.InvistaIX.model.EnderecoModel;
import br.com.invistaix.InvistaIX.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoRestController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<?> cadastrarEndereco(@ModelAttribute EnderecoModel endereco) {
		try {
			enderecoService.salvarEndereco(endereco);
			return ResponseEntity.ok("Sucesso");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@GetMapping("/findByID={id}")
	public EnderecoModel encontrarPorId(@PathVariable Integer id) {
		try {
			return enderecoService.encontrarPorId(id);
		} catch (IllegalArgumentException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@GetMapping("/findByEndereco={rua}&{numero}&{loteamento}&{cidade}&{estado}&{CEP}")
	public EnderecoModel encontrarEndereco(@PathVariable String rua ,@PathVariable Integer numero, @PathVariable String loteamento, @PathVariable String cidade, @PathVariable String estado, @PathVariable String CEP) {
		try {
			return enderecoService.encontrarEndereco(rua, numero, loteamento, cidade, estado, CEP);
		} catch (IllegalArgumentException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
