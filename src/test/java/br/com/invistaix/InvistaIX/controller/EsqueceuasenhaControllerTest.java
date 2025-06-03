package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.service.RedefinirSenhaService;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EsqueceuasenhaControllerTest {

    @Test
    public void deveRetornarOkSeEmailExiste() {
        var service = mock(RedefinirSenhaService.class);
        var controller = new EsqueceuasenhaController();
        controller.redefinirSenhaService = service;

        when(service.enviarLinkRedefinicao("teste@exemplo.com")).thenReturn(true);

        Map<String, String> resposta = controller.recuperarSenha("teste@exemplo.com");

        assertEquals("ok", resposta.get("status"));
        assertEquals("Um link de redefinição de senha foi enviado para o email", resposta.get("mensagem"));
        verify(service).enviarLinkRedefinicao("teste@exemplo.com");
    }

    @Test
    public void deveRetornarErroSeEmailNaoExiste() {
        var service = mock(RedefinirSenhaService.class);
        var controller = new EsqueceuasenhaController();
        controller.redefinirSenhaService = service;

        when(service.enviarLinkRedefinicao("naoexiste@teste.com")).thenReturn(false);

        Map<String, String> resposta = controller.recuperarSenha("naoexiste@teste.com");

        assertEquals("erro", resposta.get("status"));
        assertEquals("Email não encontrado", resposta.get("mensagem"));
        verify(service).enviarLinkRedefinicao("naoexiste@teste.com");
    }
}

