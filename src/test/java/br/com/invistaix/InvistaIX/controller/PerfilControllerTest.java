package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PerfilControllerTest {

    @InjectMocks
    private PerfilController perfilController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void exibirPerfil_usuarioNaoLogado_redirecionaLogin() {
        when(session.getAttribute("usuarioLogado")).thenReturn(null);

        String resultado = perfilController.exibirPerfil(model, session);
                assertEquals("redirect:/login", resultado);
    }

    @Test
    void excluirConta_usuarioLogado_excluiESessaoInvalida() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);

        when(session.getAttribute("usuarioLogado")).thenReturn(usuario);

        ResponseEntity<String> response = perfilController.excluirConta(session);

        verify(usuarioService).apagarUsuario(1L);
        verify(session).invalidate();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Conta excluída com sucesso", response.getBody());
    }

}
