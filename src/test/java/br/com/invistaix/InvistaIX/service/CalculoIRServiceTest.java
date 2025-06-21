package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.*;
import br.com.invistaix.InvistaIX.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculoIRServiceTest {

    private CalculoIRService calculoIRService;

    private IRRepository irRepository;
    private ImovelRepository imovelRepository;
    private DespesaRepository despesaRepository;
    private ProprietarioRepository proprietarioRepository;

    @BeforeEach
    public void setUp() throws Exception {
        calculoIRService = new CalculoIRService();

        irRepository = mock(IRRepository.class);
        imovelRepository = mock(ImovelRepository.class);
        despesaRepository = mock(DespesaRepository.class);
        proprietarioRepository = mock(ProprietarioRepository.class);

        injectMock("IRRepository", irRepository);
        injectMock("imovelRepository", imovelRepository);
        injectMock("despesaRepository", despesaRepository);
        injectMock("proprietarioRepository", proprietarioRepository);
    }

    private void injectMock(String fieldName, Object mock) throws Exception {
        Field field = CalculoIRService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(calculoIRService, mock);
    }

    @Test
    public void testSalvarIR_PessoaFisica() {
        Long idImovel = 1L;
        double receita = 5000.0;

        ImovelModel imovel = new ImovelModel();
        imovel.setId(idImovel);
        imovel.setIdProprietario(1L);

        ProprietarioModel proprietario = new ProprietarioModel();
        proprietario.setId(1L);
        proprietario.setTipoPessoa('F');

        DespesaModel despesa = new DespesaModel();
        despesa.setAgua(100.0);
        despesa.setLuz(200.0);
        despesa.setDespesaAvulsa(100.0);
        despesa.setManutencao(100.0);

        when(imovelRepository.findById(idImovel)).thenReturn(Optional.of(imovel));
        when(proprietarioRepository.findById(1L)).thenReturn(Optional.of(proprietario));
        when(despesaRepository.findByIdImovelAndDataBetween(any(), any(), any()))
                .thenReturn(Collections.singletonList(despesa));
        when(irRepository.save(any(IRModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        IRModel resultado = calculoIRService.salvarIR(receita, idImovel);

        assertNotNull(resultado);
        assertEquals(imovel, resultado.getImovel());
        assertNotNull(resultado.getValor());
        assertEquals(LocalDate.now(), resultado.getData());
    }

    @Test
    public void testSalvarIR_PessoaJuridica() {
        Long idImovel = 2L;
        double receita = 8000.0;

        ImovelModel imovel = new ImovelModel();
        imovel.setId(idImovel);
        imovel.setIdProprietario(2L);

        ProprietarioModel proprietario = new ProprietarioModel();
        proprietario.setId(2L);
        proprietario.setTipoPessoa('J');

        when(imovelRepository.findById(idImovel)).thenReturn(Optional.of(imovel));
        when(proprietarioRepository.findById(2L)).thenReturn(Optional.of(proprietario));
        when(irRepository.save(any(IRModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        IRModel resultado = calculoIRService.salvarIR(receita, idImovel);

        assertNotNull(resultado);
        assertEquals(imovel, resultado.getImovel());
        assertEquals(LocalDate.now(), resultado.getData());
        assertEquals(614.4, resultado.getValor(), 0.01);
    }
}