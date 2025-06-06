package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import br.com.invistaix.InvistaIX.repository.ImpostoRepository;
import br.com.invistaix.InvistaIX.service.ImpostoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImpostoServiceTest {

    @InjectMocks
    private ImpostoService impostoService;

    @Mock
    private ImpostoRepository impostoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarIptu_DeveSalvarQuandoDadosValidos() {
        ImpostoModel iptu = new ImpostoModel(0, 1500.0, LocalDate.of(2024, 5, 20), 1L);
        when(impostoRepository.existsByidimovelAndAno(1L, 2024)).thenReturn(false);
        when(impostoRepository.findByIdimovelAndDataBetween(eq(1L), any(), any())).thenReturn(Optional.empty());
        when(impostoRepository.save(iptu)).thenReturn(iptu);

        ImpostoModel salvo = impostoService.criarIptu(iptu);
        assertNotNull(salvo);
        verify(impostoRepository).save(iptu);
    }

    @Test
    void listarPorImovel_DeveRetornarListaQuandoIdValido() {
        List<ImpostoModel> lista = Arrays.asList(
                new ImpostoModel(1, 1000.0, LocalDate.of(2023, 1, 10), 1L)
        );
        when(impostoRepository.findByidimovel(1L)).thenReturn(lista);

        List<ImpostoModel> resultado = impostoService.listarPorImovel(1L);

        assertEquals(1, resultado.size());
        assertEquals(1000.0, resultado.get(0).getValor());
    }

    @Test
    void listarPorImovel_DeveLancarExcecaoComIdInvalido() {
        Exception ex = assertThrows(RuntimeException.class, () -> impostoService.listarPorImovel(0L));
        assertTrue(ex.getMessage().contains("ID do imóvel inválido"));
    }
}

