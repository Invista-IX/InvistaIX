package br.com.invistaix.InvistaIX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invistaix.InvistaIX.model.ProprietarioModel;
import br.com.invistaix.InvistaIX.service.ProprietarioService;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioRestController {
	
	@Autowired
	ProprietarioService proprietarioService;
	
	@PostMapping("save")
	public ResponseEntity<?> salvarProprietario(@ModelAttribute ProprietarioModel proprietario) {
		try {
			proprietarioService.salvarProprietario(proprietario);
			return ResponseEntity.ok("Sucesso");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@GetMapping("/findById={id}")
	public ProprietarioModel encontrarPorId(@PathVariable Integer id) {
		try {
			return proprietarioService.encontrarPorId(id);
		} catch (IllegalArgumentException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
	
	@GetMapping("/findByCnpjCpf={cnpjCpf}")
	public ProprietarioModel encontrarProprietarioPorCnpjCpf(@PathVariable String cnpjCpf) {
		try {
			return proprietarioService.encontrarPorCnpjCpf(cnpjCpf);
		} catch (IllegalArgumentException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
