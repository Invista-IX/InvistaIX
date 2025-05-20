package br.com.invistaix.InvistaIX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.service.GrupoService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	GrupoService grupoService;

    @GetMapping
    public String dashboardController() {
        return "dashboard";
    }
    
    @GetMapping("/cadastro_grupo")
    public String cadastrarGrupo(Model model) {
    	model.addAttribute("grupo", new GrupoModel());
    	return "dashboard/cadastro_grupo";
    }
    
    @PostMapping("/cadastro_grupo")
    public String salvarGrupo(GrupoModel grupo, Model model) {
    	grupoService.salvar(grupo);
    	return "redirect:/dashboard";
    }
    
    @GetMapping("/grupo/{id}")
    public String abrirGrupo(@PathVariable Integer id, Model model) {
    	model.addAttribute("grupo",  grupoService.encontrarPorId(id));
    	return "dashboard/grupo";
    }
}
