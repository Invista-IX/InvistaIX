package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String autenticar(String email, String senha) {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email);
        if (usuarioModel == null) {
            return "Email não encontrado";
        }

        if (!usuarioModel.getSenha().equals(senha)) {
            return "Senha incorreta.";
        }

        return null;
    }
}
