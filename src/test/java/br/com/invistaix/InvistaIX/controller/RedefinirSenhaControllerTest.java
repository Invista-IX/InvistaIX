package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.service.RedefinirSenhaService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RedefinirSenhaControllerTest {

    @Test
    public void erroSeSenhasDiferentes() {
        var controller = new RedefinirSenhaController();
        var model = mock(Model.class);

        var resultado = controller.salvarNovaSenha("email@teste.com", "123", "456", model);

        assertEquals("redefinirsenha", resultado);
        verify(model).addAttribute("erro", "As senhas não coincidem.");
    }

    @Test
    public void sucessoSeSenhaRedefinida() {
        var service = mock(RedefinirSenhaService.class);
        when(service.redefinirSenha("email@teste.com", "123")).thenReturn(true);

        var controller = new RedefinirSenhaController();
        controller.redefinirSenhaService = service;

        var model = mock(Model.class);

        var resultado = controller.salvarNovaSenha("email@teste.com", "123", "123", model);

        assertEquals("login", resultado);
        verify(model).addAttribute("mensagem", "Senha redefinida com sucesso!");
    }

    @Test
    public void erroSeFalhaRedefinirSenha() {
        var service = mock(RedefinirSenhaService.class);
        when(service.redefinirSenha("email@teste.com", "123")).thenReturn(false);

        var controller = new RedefinirSenhaController();
        controller.redefinirSenhaService = service;

        var model = mock(Model.class);

        var resultado = controller.salvarNovaSenha("email@teste.com", "123", "123", model);

        assertEquals("redefinirsenha", resultado);
        verify(model).addAttribute("erro", "Erro ao redefinir senha.");
    }
}
