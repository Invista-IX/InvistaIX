package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String exibirPerfil(Model model, HttpSession session) {
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "perfilusuario";
    }

    @PostMapping("/atualizar")
    public String atualizarUsuario(@ModelAttribute UsuarioModel usuarioAtualizado,
                                   RedirectAttributes redirectAttributes,
                                   HttpSession session) {

        UsuarioModel usuarioExistente = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuarioExistente != null && usuarioExistente.getId().equals(usuarioAtualizado.getId())) {
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
            usuarioExistente.setTipoPessoa(usuarioAtualizado.getTipoPessoa());
            usuarioExistente.setCpfCnpj(usuarioAtualizado.getCpfCnpj());

            usuarioService.salvarCadastro(usuarioExistente);

            session.setAttribute("usuarioLogado", usuarioExistente);

            redirectAttributes.addFlashAttribute("mensagem", "Dados atualizados com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar os dados.");
        }

        return "redirect:/perfil";
    }

    @DeleteMapping("/excluir")
    @ResponseBody
    public ResponseEntity<String> excluirConta(HttpSession session) {
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuario != null) {
            usuarioService.apagarUsuario(usuario.getId());
            session.invalidate();
            return ResponseEntity.ok("Conta excluída com sucesso");
        }

        return ResponseEntity.status(404).body("Usuário não encontrado");
    }

}
