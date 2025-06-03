package br.com.invistaix.InvistaIX.Model;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoModelTest {

    @Test
    void deveCriarAvaliacaoComValoresCorretos() {
        MockMultipartFile file = new MockMultipartFile("file", "teste.pdf", "application/pdf", "conteudo".getBytes());

        AvaliacaoModel avaliacao = new AvaliacaoModel(
                1L,
                300000.0,
                "12345678901234",
                10L,
                LocalDate.of(2025, 5, 29),
                "Empresa Teste",
                "documento.pdf",
                file
        );

        assertEquals(1L, avaliacao.getIdavaliacao());
        assertEquals(300000.0, avaliacao.getValorAvaliacao());
        assertEquals("12345678901234", avaliacao.getCnpj());
        assertEquals(10L, avaliacao.getIdimovel());
        assertEquals(LocalDate.of(2025, 5, 29), avaliacao.getDataAvaliacao());
        assertEquals("Empresa Teste", avaliacao.getRazaoSocial());
        assertEquals("documento.pdf", avaliacao.getDocAvaliacao());
        assertEquals(file, avaliacao.getDocAvaliacaoFile());
    }

    @Test
    void deveSetarEAcessarTodosOsCampos() {
        AvaliacaoModel avaliacao = new AvaliacaoModel();

        avaliacao.setIdavaliacao(2L);
        avaliacao.setValorAvaliacao(150000.0);
        avaliacao.setCnpj("98765432100012");
        avaliacao.setIdimovel(20L);
        avaliacao.setDataAvaliacao(LocalDate.of(2025, 1, 1));
        avaliacao.setRazaoSocial("Outra Empresa");
        avaliacao.setDocAvaliacao("arquivo.pdf");
        MockMultipartFile file = new MockMultipartFile("file", "outro.pdf", "application/pdf", "dados".getBytes());
        avaliacao.setDocAvaliacaoFile(file);

        assertEquals(2L, avaliacao.getIdavaliacao());
        assertEquals(150000.0, avaliacao.getValorAvaliacao());
        assertEquals("98765432100012", avaliacao.getCnpj());
        assertEquals(20L, avaliacao.getIdimovel());
        assertEquals(LocalDate.of(2025, 1, 1), avaliacao.getDataAvaliacao());
        assertEquals("Outra Empresa", avaliacao.getRazaoSocial());
        assertEquals("arquivo.pdf", avaliacao.getDocAvaliacao());
        assertEquals(file, avaliacao.getDocAvaliacaoFile());
    }
}

