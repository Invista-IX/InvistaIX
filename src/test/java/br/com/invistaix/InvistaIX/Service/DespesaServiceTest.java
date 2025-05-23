package br.com.invistaix.InvistaIX.Service;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.repository.DespesaRepository;
import br.com.invistaix.InvistaIX.service.DespesaService;
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
public class DespesaServiceTest {

    @InjectMocks
    private DespesaService despesaService;

    @Mock
    private DespesaRepository despesaRepository;

    private DespesaModel despesaValida;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        despesaValida = new DespesaModel();
        despesaValida.setIdImovel(1L);
        despesaValida.setData(LocalDate.of(2024, 5, 1));
        despesaValida.setAgua(100.0);
        despesaValida.setLuz(150.0);
    }

    @Test
    void deveCriarDespesaComValoresValidos() {
        when(despesaRepository.existsByImovelAndMesAndAno(1L, 5, 2024)).thenReturn(false);
        when(despesaRepository.save(any(DespesaModel.class))).thenReturn(despesaValida);

        DespesaModel resultado = despesaService.criarDespesa(despesaValida);

        assertNotNull(resultado);
        verify(despesaRepository).save(despesaValida);
    }
    @Test
    void deveLancarExcecaoSeAlgumValorForNegativo() {
        despesaValida.setLuz(-10.0);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> despesaService.criarDespesa(despesaValida)
        );

        assertEquals("Valores negativos não são permitidos.", exception.getMessage());
    }

    @Test
    void deveIgnorarValoresZerados() {
        despesaValida.setLuz(0.0);
        despesaValida.setAgua(null);
        despesaValida.setManutencao(50.0);

        when(despesaRepository.existsByImovelAndMesAndAno(1L, 5, 2024)).thenReturn(false);
        when(despesaRepository.save(any(DespesaModel.class))).thenReturn(despesaValida);

        DespesaModel resultado = despesaService.criarDespesa(despesaValida);
        assertNull(resultado.getLuz());
    }

    @Test
    void deveLancarExcecaoSeDataForNula() {
        despesaValida.setData(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> despesaService.criarDespesa(despesaValida)
        );

        assertEquals("A data deve ser informada.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoSeNenhumValorDeDespesaInformado() {
        despesaValida.setAgua(null);
        despesaValida.setLuz(null);
        despesaValida.setManutencao(null);
        despesaValida.setDespesaAvulsa(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> despesaService.criarDespesa(despesaValida)
        );

        assertEquals("Informe ao menos um valor de despesa.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoSeJaExisteDespesaNoMes() {
        when(despesaRepository.existsByImovelAndMesAndAno(1L, 5, 2024)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> despesaService.criarDespesa(despesaValida)
        );

        assertEquals("Já existe despesa no mês.", exception.getMessage());
    }
    @Test
    void deveListarDespesasPorImovel() {
        when(despesaRepository.findByIdImovel(1L)).thenReturn(List.of(despesaValida));

        List<DespesaModel> resultado = despesaService.listarPorImovel(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(despesaRepository).findByIdImovel(1L);
    }

    @Test
    void deveLancarExcecaoSeIdImovelForNuloOuInvalido_listarPorImovel() {
        IllegalArgumentException exNull = assertThrows(IllegalArgumentException.class, () -> {
            despesaService.listarPorImovel(null);
        });
        assertEquals("ID do imóvel inválido.", exNull.getMessage());

        List<Long> idsInvalidos = List.of(0L, -1L);
        for (Long id : idsInvalidos) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
                despesaService.listarPorImovel(id);
            });
            assertEquals("ID do imóvel inválido.", ex.getMessage());
        }
    }
    @Test
    void deveListarDespesasPorPeriodo() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 12, 31);

        when(despesaRepository.findByIdImovelAndDataBetween(1L, inicio, fim))
                .thenReturn(List.of(despesaValida));

        List<DespesaModel> resultado = despesaService.listarPorPeriodo(1L, inicio, fim);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(despesaRepository).findByIdImovelAndDataBetween(1L, inicio, fim);
    }

    @Test
    void deveLancarExcecaoSeIdImovelOuDatasForemInvalidas() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 12, 31);

        assertThrows(IllegalArgumentException.class, () -> despesaService.listarPorPeriodo(null, inicio, fim));
        assertThrows(IllegalArgumentException.class, () -> despesaService.listarPorPeriodo(0L, inicio, fim));
        assertThrows(IllegalArgumentException.class, () -> despesaService.listarPorPeriodo(1L, null, fim));
        assertThrows(IllegalArgumentException.class, () -> despesaService.listarPorPeriodo(1L, inicio, null));
    }

    @Test
    void deveLancarExcecaoSeDataFinalForAntesDaInicial() {
        LocalDate inicio = LocalDate.of(2024, 5, 1);
        LocalDate fim = LocalDate.of(2024, 4, 30);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> despesaService.listarPorPeriodo(1L, inicio, fim)
        );

        assertEquals("Data final deve ser igual ou posterior à data inicial.", ex.getMessage());
    }
    @Test
    void deveExcluirDespesaComIdValido() {
        Long id = 10L;
        doNothing().when(despesaRepository).deleteById(id);

        assertDoesNotThrow(() -> despesaService.excluir(id));
        verify(despesaRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoSeIdDespesaForInvalido() {
        List<Long> idsInvalidos = new ArrayList<>();
        idsInvalidos.add(null);
        idsInvalidos.add(0L);
        idsInvalidos.add(-5L);

        for (Long id : idsInvalidos) {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
                despesaService.excluir(id);
            });
            assertEquals("ID da despesa inválido.", ex.getMessage());
        }
    }
}