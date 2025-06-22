package br.com.invistaix.InvistaIX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.service.GrupoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	GrupoService grupoService;

    @GetMapping
    public String dashboardController(Model model, HttpSession session) {
    	//bloco de validação de usuário loogado
    	UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");
    	
    	if (usuario == null) {
            return "redirect:/login";
        }
    	//
    	
    	model.addAttribute("usuario", usuario);
        return "dashboard";
    }
    
    @GetMapping("/cadastrarGrupo")
    public String cadastrarGrupo(Model model, HttpSession session) {
    	//bloco de validação de usuário loogado
    	UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");
    	
    	if (usuario == null) {
            return "redirect:/login";
        }
    	//
    	
    	model.addAttribute("grupo", new GrupoModel());
    	return "dashboard/cadastroGrupo";
    }
    
    @PostMapping("/cadastro_grupo")
    public String salvarGrupo(GrupoModel grupo, Model model, HttpSession session) {
    	//bloco de validação de usuário loogado
    	UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");
    	
    	if (usuario == null) {
            return "redirect:/login";
        }
    	//
    	
    	grupoService.salvar(grupo);
    	return "redirect:/dashboard";
    }
    
    @GetMapping("/grupo={id}")
    public String abrirGrupo(@PathVariable Long id, Model model, HttpSession session) {
    	//bloco de validação de usuário loogado
    	UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");
    	
    	if (usuario == null) {
            return "redirect:/login";
        }
    	//
    	
    	model.addAttribute("usuario", usuario);
    	model.addAttribute("grupo",  grupoService.encontrarPorId(id));
    	return "dashboard/grupo";
    }
}
