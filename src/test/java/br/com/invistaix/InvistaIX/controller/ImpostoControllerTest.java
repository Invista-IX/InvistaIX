package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import br.com.invistaix.InvistaIX.service.ImpostoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ImpostoController.class)
class ImpostoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImpostoService impostoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void cadastroComDadosValidosRetornaSucesso() throws Exception {
        ImpostoModel iptu = new ImpostoModel();
        iptu.setValor(1000.0);
        iptu.setIdimovel(1L);
        iptu.setData(LocalDate.of(2025, 5, 23));

        when(impostoService.criarIptu(any(ImpostoModel.class))).thenReturn(new ImpostoModel());

        mockMvc.perform(post("/iptu/criar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("valor", iptu.getValor().toString())
                        .param("idimovel", iptu.getIdimovel().toString())
                        .param("data", iptu.getData().toString())
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Sucesso"));

        verify(impostoService, times(1)).criarIptu(any(ImpostoModel.class));
    }

    @Test
    void cadastroComDadosInvalidosRetornaBadRequest() throws Exception {

        doThrow(new IllegalArgumentException("Erro: o valor do IPTU deve ser um número positivo maior que zero."))
                .when(impostoService).criarIptu(any(ImpostoModel.class));

        mockMvc.perform(post("/iptu/criar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("valor", "0")
                        .param("idimovel", "1")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erro: o valor do IPTU deve ser um número positivo maior que zero."));

        verify(impostoService, times(1)).criarIptu(any(ImpostoModel.class));
    }

    @Test
    void cadastroDeIPTUJaExistenteRetornaBadRequest() throws Exception {
        doThrow(new IllegalArgumentException("Já existe um IPTU cadastrado para este imóvel neste ano."))
                .when(impostoService).criarIptu(any(ImpostoModel.class));

        mockMvc.perform(post("/iptu/criar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("valor", "1000")
                        .param("idimovel", "1")
                        .param("data", "2025-05-23")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Já existe um IPTU cadastrado para este imóvel neste ano."));

        verify(impostoService, times(1)).criarIptu(any(ImpostoModel.class));
    }

    @Test
    void cadastroComErroNoServicoRetornaStatus500() throws Exception {
        doThrow(new RuntimeException("Erro inesperado"))
                .when(impostoService).criarIptu(any(ImpostoModel.class));

        mockMvc.perform(post("/iptu/criar")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("valor", "1000")
                        .param("idimovel", "1")
                        .param("data", "2025-05-23")
                )
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Erro ao salvar IPTU: Erro inesperado"));

        verify(impostoService, times(1)).criarIptu(any(ImpostoModel.class));
    }
}

