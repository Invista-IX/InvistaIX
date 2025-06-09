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
}
