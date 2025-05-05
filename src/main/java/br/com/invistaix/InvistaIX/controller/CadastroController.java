package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import br.com.invistaix.InvistaIX.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute Usuario cadastro, Model model) {

        if (usuarioService.checarEmailCadastrado(cadastro)) {
            model.addAttribute("erro", "Email já cadastrado.");
            return "cadastro";
        }

        /* debug */
        System.out.println("novo cadastro, nome: " + cadastro.getNome());

        usuarioService.salvarCadastro(cadastro);
        return "redirect:/login";
    }
}