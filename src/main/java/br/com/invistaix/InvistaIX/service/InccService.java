package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.model.InccModel;
import br.com.invistaix.InvistaIX.repository.InccRepository;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InccService {

    private static final Logger logger = LoggerFactory.getLogger(InccService.class);

    @Autowired
    private InccRepository inccRepository;

    public LocalDate getHoje() {
        return LocalDate.now();
    }


    @Scheduled(cron = "0 0 0 15 * *")
    public void executarDia15DoMes() throws IOException {
        try {
            InccModel inccModel = new InccModel(Double.parseDouble(obterUltimoValorINCC().replace("%", "").replace(",", ".")), getHoje());
            inccRepository.save(inccModel);
        } catch (IOException e) {
            logger.error("Erro de IO ao buscar o valor do INCC", e);
        } catch (RuntimeException e) {
            logger.error("Erro inesperado ao processar INCC", e);
        }
    }

    public String obterUltimoValorINCC() throws IOException {
        String url = "https://www.yahii.com.br/incc.html";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/125.0.0.0 Safari/537.36";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(userAgent)
                    .get();

            Elements tabelas = doc.select("table");
            Element tabela = tabelas.get(5);

            List<String> valoresINCC = new ArrayList<>();

            Elements linhas = tabela.select("tr");
            for (Element linha : linhas) {
                Elements colunas = linha.select("td");

                if (colunas.size() < 2) continue;

                for (int i = 1; i < colunas.size() - 1; i++) {
                    String texto = colunas.get(i).text().trim();
                    if (texto.matches("\\d{1,2},\\d{2}%")) {
                        valoresINCC.add(texto);
                    }
                }
            }

            if (!valoresINCC.isEmpty()) {
                return valoresINCC.get(valoresINCC.size() - 1);
            } else {
                throw new RuntimeException("Nenhum valor de INCC encontrado.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public List<InccModel> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        try {
            if (inicio == null || fim == null) {
                throw new IllegalArgumentException("Período inválido: datas devem ser informadas.");
            }
            if (fim.isBefore(inicio)) {
                throw new IllegalArgumentException("Data final deve ser igual ou posterior à data inicial.");
            }
            List<InccModel> inncs = inccRepository.findByDataBetween(inicio, fim);
            return inncs;

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar incc por período: " + ex.getMessage(), ex);
        }
    }

}