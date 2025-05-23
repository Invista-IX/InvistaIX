package br.com.invistaix.InvistaIX.controller;

import static org.junit.jupiter.api.Assertions.*;
import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.service.DespesaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

public class DespesaControllerTest {

    @Mock
    private DespesaService despesaService;

    @InjectMocks
    private DespesaController despesaController;

    public DespesaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarDespesaComSucesso() {
        DespesaModel despesa = new DespesaModel();
        despesa.setManutencao(100.0);
        despesa.setDespesaAvulsa(50.0);
        despesa.setLuz(30.0);
        despesa.setAgua(20.0);
        despesa.setData(LocalDate.now());
        despesa.setIdImovel(1L);

        ResponseEntity<?> response = despesaController.cadastrarDespesa(despesa);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sucesso", response.getBody());
    }

    @Test
    void deveRetornarBadRequestQuandoDespesaForInvalida() {
        DespesaModel despesa = new DespesaModel();
        despesa.setIdImovel(-1L);
        despesa.setData(LocalDate.now());

        ResponseEntity<?> response = despesaController.cadastrarDespesa(despesa);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ID do imóvel inválido.", response.getBody());
    }

    @Test
    void deveRetornarErro500QuandoServicoLancarErroNaoEsperado() {
        DespesaModel despesa = new DespesaModel();
        despesa.setIdImovel(1L);
        despesa.setData(LocalDate.now());

        ResponseEntity<?> response = despesaController.cadastrarDespesa(despesa);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro inesperado", response.getBody());
    }
}
