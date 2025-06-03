package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import br.com.invistaix.InvistaIX.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class AvaliacaoControllerTest {

    @Mock
    private AvaliacaoService avaliacaoService;
    @InjectMocks
    private AvaliacaoController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deveRetornarErroAoSalvarAvaliacao() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setDocAvaliacaoFile(new MockMultipartFile("file", "teste.pdf", "application/pdf", "conteudo".getBytes()));

        doThrow(new RuntimeException("Falha no banco")).when(avaliacaoService).criarAvaliacao(any());

        ResponseEntity<?> response = controller.cadastrarAvaliacao(avaliacao);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Erro ao salvar avaliação"));
    }

}
