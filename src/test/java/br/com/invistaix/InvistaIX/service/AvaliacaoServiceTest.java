package br.com.invistaix.InvistaIX.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import br.com.invistaix.InvistaIX.repository.AvaliacaoRepository;

public class AvaliacaoServiceTest {

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void criarAvaliacao_Valida_DeveSalvarComSucesso() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdimovel(1L);
        avaliacao.setValorAvaliacao(100000.0);
        avaliacao.setDataAvaliacao(LocalDate.of(2025, 5, 29));

        when(avaliacaoRepository.existsByidimovelAndAno(1L, 2025)).thenReturn(false);
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacao);

        AvaliacaoModel resultado = avaliacaoService.criarAvaliacao(avaliacao);

        assertNotNull(resultado);
        assertEquals(avaliacao.getIdimovel(), resultado.getIdimovel());
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

}
