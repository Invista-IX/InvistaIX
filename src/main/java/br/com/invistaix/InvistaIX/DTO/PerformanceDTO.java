package br.com.invistaix.InvistaIX.DTO;

import java.time.LocalDate;

public class PerformanceDTO {
    private double porcentagem;
    private LocalDate data;
    private int indice;

    public PerformanceDTO() {}

    public PerformanceDTO(double porcentagem, LocalDate data, int indice) {
        this.porcentagem = porcentagem;
        this.data = data;
        this.indice = indice;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
}
