package br.com.invistaix.InvistaIX.api;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invistaix.InvistaIX.DTO.ImagemDTO;
import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoRestController {
	
	@Autowired
	GrupoService grupoService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarGrupo(@ModelAttribute GrupoModel grupo) {
		try {
			String resultado = grupoService.salvar(grupo);
			return ResponseEntity.ok(resultado);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idGrupo}/atualizarImagem")
	public ResponseEntity<?> atualizarImagem(@PathVariable Long idGrupo, @ModelAttribute ImagemDTO imagem ) {
		try {
			System.out.println("imagem: " + imagem);
			String resultado = grupoService.atualizarImagem(idGrupo, imagem.getBase64());
			return ResponseEntity.ok(resultado);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/encontrarGrupos={idGestor}")
	public ResponseEntity<?> encontrarGrupos(@PathVariable Long idGestor) {
		try {
			Set<GrupoModel> grupos = grupoService.encontrarGrupos(idGestor);
			System.out.println(grupos);
			Iterator<GrupoModel> gps = grupos.iterator();
			while (gps.hasNext()) {
				System.out.println(gps.next().getUsuarios().size());
			}
			return ResponseEntity.ok(grupos);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@GetMapping("/encontrar/codigo={codigo}&senha={senha}")
	public ResponseEntity<?> encontrarGrupo(@PathVariable String codigo, @PathVariable String senha) {
		try {
			GrupoModel grupo = grupoService.encontrarPorCodigo(codigo, senha);
			return ResponseEntity.ok(grupo);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idGrupo}/adicionarGestor={idGestor}")
	public ResponseEntity<?> atribuirProprietarioGrupo(@PathVariable Long idGrupo, @PathVariable Long idGestor) {
		try {
			grupoService.atribuirGrupo(idGrupo, idGestor);
			return ResponseEntity.ok("Gestor adicionado ao grupo com sucesso.");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@PatchMapping("/{idGrupo}/removerGestor={idGestor}")
	public ResponseEntity<?> removerProprietarioGrupo(@PathVariable Long idGrupo, @PathVariable Long idGestor) {
		try {
			grupoService.desatribuirGrupo(idGrupo, idGestor);
			return ResponseEntity.ok("Gestor removido do grupo com sucesso.");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
	
	@GetMapping("/{idGrupo}/totalGestores")
	public ResponseEntity<?> retornarTotalUsuariosNoGrupo(@PathVariable Long idGrupo) {
		try {
			GrupoModel grupo = grupoService.encontrarPorId(idGrupo);
			return ResponseEntity.ok(grupo.getUsuarios().size());
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (Exception ex) {
			return ResponseEntity.status(500).body(ex.getMessage());
		}
	}
}
