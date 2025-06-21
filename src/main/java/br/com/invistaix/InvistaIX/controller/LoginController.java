package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.service.LoginService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        Model model,
                        HttpSession session) {

        UsuarioModel usuario = loginService.autenticar(email, senha);

        if (usuario == null) {
            model.addAttribute("erro", "E-mail ou senha inválidos");
            return "login";
        }

        session.setAttribute("usuarioLogado", usuario);

        return "redirect:/dashboard";
    }

}
