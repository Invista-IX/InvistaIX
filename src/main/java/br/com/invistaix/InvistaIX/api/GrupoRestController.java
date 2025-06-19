package br.com.invistaix.InvistaIX.api;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoRestController {
	
	@Autowired
	GrupoService grupoService;
	
	@GetMapping("/encontrarGrupos={idGestor}")
	public ResponseEntity<?> encontrarGrupos(@PathVariable Long idGestor) {
		try {
			Set<GrupoModel> grupos = grupoService.encontrarGrupos(idGestor);
			return ResponseEntity.ok(grupos);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@GetMapping("/todos")
	public List<GrupoModel> getGrupos() {
		return grupoService.listarTodos();
	}
	
	@PutMapping("/{idGrupo}/adicionarGestor={idGestor}")
	public ResponseEntity<?> setarProprietarioGrupo(@PathVariable Long idGrupo, @PathVariable Long idGestor) {
		try {
			grupoService.atribuirGrupo(idGrupo, idGestor);
			return ResponseEntity.ok("Gestor adicionado ao grupo com sucesso.");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
}
