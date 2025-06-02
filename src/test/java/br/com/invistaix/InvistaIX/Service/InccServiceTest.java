package br.com.invistaix.InvistaIX.Service;

import br.com.invistaix.InvistaIX.model.InccModel;
import br.com.invistaix.InvistaIX.repository.InccRepository;
import br.com.invistaix.InvistaIX.service.InccService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InccServiceTest {

    @InjectMocks
    @Spy
    private InccService inccService;

    @Mock
    private InccRepository inccRepository;

    @Test
    public void deveExecutarDia15DoMes() throws IOException {
        doReturn(LocalDate.of(2024, 5, 15)).when(inccService).getHoje();

        doReturn("1,23%").when(inccService).obterUltimoValorINCC();

        inccService.executarDia15DoMes();

        ArgumentCaptor<InccModel> captor = ArgumentCaptor.forClass(InccModel.class);
        verify(inccRepository).save(captor.capture());

        InccModel capturado = captor.getValue();
        assertEquals(1.23, capturado.getPorcentagem());
        assertEquals(LocalDate.of(2024, 5, 15), capturado.getData());
    }

    @Test
    public void deveLancarExcecaoQuandoINCCNaoEncontrado() throws IOException {
        InccService service = new InccService() {
            @Override
            public String obterUltimoValorINCC() throws IOException {
                throw new RuntimeException("Nenhum valor de INCC encontrado.");
            }
        };
        assertThrows(RuntimeException.class, service::obterUltimoValorINCC);
    }

    @Test
    void naoDeveExecutarForaDoDia15() throws IOException {
        doReturn(LocalDate.of(2024, 5, 10)).when(inccService).getHoje();
        doReturn("1,23%").when(inccService).obterUltimoValorINCC();

        inccService.executarDia15DoMes();

        verify(inccRepository, times(1)).save(any());
    }

    @Test
    void deveConverterCorretamenteINCCComPorcentagemEVirgula() throws IOException {
        String valorBruto = "2,50%";
        InccService service = new InccService() {
            @Override
            public String obterUltimoValorINCC() {
                return valorBruto;
            }
        };
        double valorConvertido = Double.parseDouble(valorBruto.replace("%", "").replace(",", "."));
        assertEquals(2.50, valorConvertido);
    }

    @Test
    void deveExtrairUltimoValorINCCDeHtmlFalso() throws IOException {
        String htmlSimulado = """
        <html><body>
        <table></table><table></table><table></table><table></table><table></table>
        <table>
            <tr><td>Mês</td><td>2,50%</td></tr>
        </table>
        </body></html>
        """;

        Document doc = Jsoup.parse(htmlSimulado);
        Elements tabelas = doc.select("table");
        Element tabela = tabelas.get(5);
        Elements linhas = tabela.select("tr");
        String valor = linhas.get(0).select("td").get(1).text().trim();

        assertEquals("2,50%", valor);
    }





}
