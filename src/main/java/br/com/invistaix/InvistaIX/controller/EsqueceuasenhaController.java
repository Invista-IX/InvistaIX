package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.service.RedefinirSenhaService;
import br.com.invistaix.InvistaIX.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EsqueceuasenhaController {

    @Autowired
    RedefinirSenhaService redefinirSenhaService;

    @GetMapping("/esqueceuasenha")
    public String mostrarPaginaEsqueceuSenha() {
        return "esqueceuasenha";
    }

    @PostMapping("/esqueceuasenha")
    @ResponseBody
    public Map<String, String> recuperarSenha(@RequestParam("email") String email){
        boolean enviado = redefinirSenhaService.enviarLinkRedefinicao(email);
        Map<String, String> resposta = new HashMap<>();

        if (enviado) {
            resposta.put("status", "ok");
            resposta.put("mensagem", "Um link de redefinição de senha foi enviado para o email");
        } else {
            resposta.put("status", "erro");
            resposta.put("mensagem", "Email não encontrado");
        }

        return resposta;
    }
}
