package br.com.invistaix.InvistaIX.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.service.GrupoService;

@RestController
@RequestMapping("/api")
public class APIGruposRestController {
	
	@Autowired
	GrupoService grupoService;
	
	@GetMapping("/grupos")
	public List<GrupoModel> getGrupos() {
		return grupoService.listarTodos();
	}
}
