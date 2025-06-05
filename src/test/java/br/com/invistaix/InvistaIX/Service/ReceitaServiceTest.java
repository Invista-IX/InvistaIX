package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ReceitaModel;
import br.com.invistaix.InvistaIX.repository.ReceitaRepository;
import br.com.invistaix.InvistaIX.service.ReceitaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReceitaServiceTest {

    @InjectMocks
    private ReceitaService receitaService;

    @Mock
    private ReceitaRepository receitaRepository;

    private ReceitaModel receitaValida;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        receitaValida = new ReceitaModel();
        receitaValida.setIdImovel(1L);
        receitaValida.setAluguel(1000.0);
        receitaValida.setReceitaAvulsa(200.0);
        receitaValida.setData(LocalDate.of(2024, 5, 1));
    }

    @Test
    void deveCriarReceitaComValoresValidos() {
        when(receitaRepository.existsByImovelAndMesAndAno(1L, 5, 2024)).thenReturn(false);
        when(receitaRepository.save(any(ReceitaModel.class))).thenReturn(receitaValida);

        ReceitaModel resultado = receitaService.criarReceita(receitaValida);

        assertNotNull(resultado);
        verify(receitaRepository).save(receitaValida);
    }

    @Test
    void deveLancarExcecaoSeAluguelNegativo() {
        receitaValida.setAluguel(-1.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            receitaService.criarReceita(receitaValida);
        });
        assertEquals("Valores negativos não são permitidos.", ex.getMessage());
    }

    @Test
    void deveIgnorarAluguelZerado() {
        receitaValida.setAluguel(0.0);
        receitaValida.setReceitaAvulsa(100.0);

        when(receitaRepository.existsByImovelAndMesAndAno(1L, 5, 2024)).thenReturn(false);
        when(receitaRepository.save(any(ReceitaModel.class))).thenReturn(receitaValida);

        ReceitaModel resultado = receitaService.criarReceita(receitaValida);
        assertNull(resultado.getAluguel());
        verify(receitaRepository).save(receitaValida);
    }

    @Test
    void deveLancarExcecaoSeDataNaoInformada() {
        receitaValida.setData(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            receitaService.criarReceita(receitaValida);
        });
        assertEquals("A data deve ser informada.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeNenhumValorReceitaInformado() {
        receitaValida.setAluguel(null);
        receitaValida.setReceitaAvulsa(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            receitaService.criarReceita(receitaValida);
        });
        assertEquals("Informe ao menos um valor de receita.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeReceitaJaExistirNoMes() {
        when(receitaRepository.existsByImovelAndMesAndAno(1L, 5, 2024)).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            receitaService.criarReceita(receitaValida);
        });
        assertEquals("Já existe receita no mês.", ex.getMessage());
    }

    @Test
    void deveListarPorImovelComIdValido() {
        when(receitaRepository.findByIdImovel(1L)).thenReturn(List.of(receitaValida));

        List<ReceitaModel> receitas = receitaService.listarPorImovel(1L);

        assertFalse(receitas.isEmpty());
        verify(receitaRepository).findByIdImovel(1L);
    }

    @Test
    void deveLancarExcecaoSeIdImovelForNuloOuInvalido_listarPorImovel() {
        List<Long> idsInvalidos = new ArrayList<>();
        idsInvalidos.add(null);
        idsInvalidos.add(0L);
        idsInvalidos.add(-1L);

        for (Long id : idsInvalidos) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
                receitaService.listarPorImovel(id);
            });
            assertEquals("ID do imóvel inválido.", ex.getMessage());
        }
    }

    @Test
    void deveListarPorPeriodoComDadosValidos() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 12, 31);

        when(receitaRepository.findByIdImovelAndDataBetween(1L, inicio, fim)).thenReturn(List.of(receitaValida));

        List<ReceitaModel> receitas = receitaService.listarPorPeriodo(1L, inicio, fim);

        assertFalse(receitas.isEmpty());
        verify(receitaRepository).findByIdImovelAndDataBetween(1L, inicio, fim);
    }

    @Test
    void deveLancarExcecaoSeIdImovelForNuloOuInvalido_listarPorPeriodo() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 12, 31);
        List<Long> idsInvalidos = new ArrayList<>();
        idsInvalidos.add(null);
        idsInvalidos.add(0L);
        idsInvalidos.add(-1L);

        for (Long id : idsInvalidos) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
                receitaService.listarPorPeriodo(id, inicio, fim);
            });
            assertEquals("ID do imóvel inválido.", ex.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoSeDatasNaoForemInformadas_listarPorPeriodo() {
        LocalDate inicio = null;
        LocalDate fim = null;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            receitaService.listarPorPeriodo(1L, inicio, fim);
        });
        assertEquals("Período inválido: datas devem ser informadas.", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeDataFinalAntesDataInicial_listarPorPeriodo() {
        LocalDate inicio = LocalDate.of(2024, 12, 31);
        LocalDate fim = LocalDate.of(2024, 1, 1);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            receitaService.listarPorPeriodo(1L, inicio, fim);
        });
        assertEquals("Data final deve ser igual ou posterior à data inicial.", ex.getMessage());
    }

    @Test
    void deveExcluirComIdValido() {
        doNothing().when(receitaRepository).deleteById(1L);
        receitaService.excluir(1L);
        verify(receitaRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoSeIdReceitaForInvalido() {
        List<Long> idsInvalidos = new ArrayList<>();
        idsInvalidos.add(null);
        idsInvalidos.add(0L);
        idsInvalidos.add(-5L);

        for (Long id : idsInvalidos) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
                receitaService.excluir(id);
            });
            assertEquals("ID da receita inválido.", ex.getMessage());
        }
    }
}
