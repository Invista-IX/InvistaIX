package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.GraficoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraficoService {
    public GraficoModel buscarDadosGrafico() {
        GraficoModel receitaDespesa = new GraficoModel();
        receitaDespesa.setMeses(List.of("Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"));
        receitaDespesa.setReceita(List.of(1000d,3000d,3500d,3700d,3600d,3600d,3700d,3800d,3900d,4000d,4200d,4100d));
        receitaDespesa.setDespesa(List.of(0d,2000d,3000d,3200d,3100d,3100d,3200d,3300d,3400d,3700d,3600d,3600d));
        return receitaDespesa;
    }
}
