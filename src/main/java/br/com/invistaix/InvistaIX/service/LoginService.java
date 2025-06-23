package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel autenticar(String email, String senha) {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email);

        if (usuarioModel == null) return null;

        if (!usuarioModel.getSenha().equals(senha)) return null;

        return usuarioModel;
    }
}

