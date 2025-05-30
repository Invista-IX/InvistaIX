package br.com.invistaix.InvistaIX.Service;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import br.com.invistaix.InvistaIX.repository.AvaliacaoRepository;
import br.com.invistaix.InvistaIX.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void criarAvaliacao_JaExisteParaAno_DeveLancarExcecao() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setIdimovel(1L);
        avaliacao.setValorAvaliacao(100000.0);
        avaliacao.setDataAvaliacao(LocalDate.of(2025, 5, 29));

        when(avaliacaoRepository.existsByidimovelAndAno(1L, 2025)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            avaliacaoService.criarAvaliacao(avaliacao);
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertTrue(cause instanceof IllegalArgumentException);
        assertEquals("Já existe uma avaliacao cadastrada para este imóvel neste ano.", cause.getMessage());

        verify(avaliacaoRepository, never()).save(any());
    }
}
