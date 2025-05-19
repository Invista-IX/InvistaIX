package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.service.RedefinirSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedefinirSenhaController {

    @Autowired
    private RedefinirSenhaService redefinirSenhaService;

    @GetMapping("/redefinirsenha")
    public String mostrarPaginaRedefinicao(Model model, @RequestParam(value = "email", required = false) String email) {
        if (email == null) {
            return "redirect:/login";
        }
        model.addAttribute("email", email);
        return "redefinirsenha";

    }


    @PostMapping("/redefinirsenha")
    public String salvarNovaSenha(@RequestParam("email") String email, @RequestParam("novaSenha") String novaSenha, @RequestParam("confirmarSenha") String confirmarSenha, Model model) {
        if (!novaSenha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            return "redefinirsenha";
        }
        boolean sucesso = redefinirSenhaService.redefinirSenha(email, novaSenha);
        if (sucesso) {
            model.addAttribute("mensagem", "Senha redefinida com sucesso!");
            return "login";
        } else {
            model.addAttribute("erro", "Erro ao redefinir senha.");
            return "redefinirsenha";
        }
    }
}
