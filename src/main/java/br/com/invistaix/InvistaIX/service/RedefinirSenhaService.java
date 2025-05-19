package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class RedefinirSenhaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender emailSender;

    public boolean enviarLinkRedefinicao(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            String linkRedefinicao = "http://localhost:8080/redefinirsenha?email=" + email;
            enviarEmail(email, linkRedefinicao);
            return true;
        }
        return false;
    }


    private void enviarEmail(String email, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Redefinir Senha");
        message.setText("InvistaIX. Clique no link para redefinir sua senha: " + link);
        emailSender.send(message);
    }


    public boolean redefinirSenha(String email, String novaSenha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            usuario.setSenha(novaSenha);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }
}

