package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.ReceitaModel;
import br.com.invistaix.InvistaIX.service.ReceitaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReceitaControllerTest {

    @Mock
    private ReceitaService receitaService;

    @InjectMocks
    private ReceitaController receitaController;

    public ReceitaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarReceitaComSucesso() {
        ReceitaModel receita = new ReceitaModel();
        receita.setAluguel(1000.0);
        receita.setReceitaAvulsa(500.0);
        receita.setData(LocalDate.now());
        receita.setIdImovel(1L);

        Mockito.when(receitaService.criarReceita(receita)).thenReturn(null);

        ResponseEntity<?> response = receitaController.cadastrarReceita(receita);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sucesso", response.getBody());
    }

    @Test
    void deveRetornarBadRequestQuandoReceitaForInvalida() {
        ReceitaModel receita = new ReceitaModel();
        receita.setIdImovel(-1L);
        receita.setData(LocalDate.now());

        Mockito.doThrow(new IllegalArgumentException("ID do imóvel inválido."))
                .when(receitaService).criarReceita(receita);

        ResponseEntity<?> response = receitaController.cadastrarReceita(receita);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ID do imóvel inválido.", response.getBody());
    }

    @Test
    void deveRetornarErro500QuandoServicoLancarErroNaoEsperado() {
        ReceitaModel receita = new ReceitaModel();
        receita.setIdImovel(1L);
        receita.setData(LocalDate.now());

        Mockito.doThrow(new RuntimeException("Erro inesperado"))
                .when(receitaService).criarReceita(receita);

        ResponseEntity<?> response = receitaController.cadastrarReceita(receita);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro inesperado", response.getBody());
    }
}
