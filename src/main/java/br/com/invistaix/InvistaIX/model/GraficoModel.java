package br.com.invistaix.InvistaIX.model;

import java.util.List;

public class GraficoModel {
    private List<String> meses;
    private List<Double> receita;
    private List<Double> despesa;
    private List<Double> lucro;

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
    }

    public List<Double> getReceita() {
        return receita;
    }

    public void setReceita(List<Double> receita) {
        this.receita = receita;
    }

    public List<Double> getDespesa() {
        return despesa;
    }

    public void setDespesa(List<Double> despesa) {
        this.despesa = despesa;
    }

    public List<Double> getLucro() {
        return lucro;
    }

    public void setLucro(List<Double> lucro) {
        this.lucro = lucro;
    }
}

