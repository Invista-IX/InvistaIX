package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            model.addAttribute("erro", "Email já cadastrado.");
            return "cadastro";
        }

        usuarioRepository.save(usuario);
        return "redirect:/login";
    }
}