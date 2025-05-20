package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
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
        model.addAttribute("usuario", new UsuarioModel());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute UsuarioModel cadastro, Model model) {

        boolean temErro = false;

        if (usuarioService.checarEmailCadastrado(cadastro)) {
            model.addAttribute("emailErro", "Email já cadastrado.");
            temErro = true;
        }

        if (usuarioService.checarCPFCadastrado(cadastro)) {
            model.addAttribute("cpfCnpjErro", "CPF/CNPJ já cadastrado.");
            temErro = true;
        }

        if (temErro) {
            return "cadastro";
        }
        /* debug */
        System.out.println("novo cadastro, nome: " + cadastro.getNome());

        usuarioService.salvarCadastro(cadastro);
        return "redirect:/login";
    }
}