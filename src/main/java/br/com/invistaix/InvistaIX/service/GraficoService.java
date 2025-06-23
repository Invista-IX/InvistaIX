package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.model.GraficoModel;
import br.com.invistaix.InvistaIX.model.ReceitaModel;
import br.com.invistaix.InvistaIX.repository.DespesaRepository;
import br.com.invistaix.InvistaIX.repository.ReceitaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class GraficoService {

    private final ReceitaRepository recRepo;
    private final DespesaRepository despRepo;

    public GraficoService(ReceitaRepository recRepo, DespesaRepository despRepo) {
        this.recRepo = recRepo;
        this.despRepo = despRepo;
    }

    public GraficoModel montarGrafico(Long idImovel, int ano) {
        List<String> meses = List.of("Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez");
        List<Double> receitas = new ArrayList<>();
        List<Double> despesas = new ArrayList<>();
        List<Double> lucros = new ArrayList<>();

        for (int m = 1; m <= 12; m++) {
            LocalDate ini = LocalDate.of(ano, m, 1);
            LocalDate fim = ini.withDayOfMonth(ini.lengthOfMonth());

            double totalRec = recRepo.findByIdImovelAndDataBetween(idImovel, ini, fim)
                    .stream()
                    .mapToDouble(r -> (r.getAluguel() != null ? r.getAluguel() : 0.0) +
                            (r.getReceitaAvulsa() != null ? r.getReceitaAvulsa() : 0.0))
                    .sum();

            double totalDesp = despRepo.findByIdImovelAndDataBetween(idImovel, ini, fim)
                    .stream()
                    .mapToDouble(d -> (d.getAgua() != null ? d.getAgua() : 0.0) +
                            (d.getLuz() != null ? d.getLuz() : 0.0) +
                            (d.getManutencao() != null ? d.getManutencao() : 0.0) +
                            (d.getDespesaAvulsa() != null ? d.getDespesaAvulsa() : 0.0))
                    .sum();

            receitas.add(totalRec);
            despesas.add(totalDesp);
            lucros.add(totalRec - totalDesp);
        }

        GraficoModel g = new GraficoModel();
        g.setMeses(meses);
        g.setReceita(receitas);
        g.setDespesa(despesas);
        g.setLucro(lucros);
        return g;
    }

}