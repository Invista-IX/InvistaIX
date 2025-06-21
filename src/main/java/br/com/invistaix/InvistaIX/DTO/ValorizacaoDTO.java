package br.com.invistaix.InvistaIX.DTO;

import java.time.LocalDate;

public class ValorizacaoDTO {
    private String valor;
    private LocalDate data;
    private int indice;

    public ValorizacaoDTO() {}

    public ValorizacaoDTO(String valor, LocalDate data, int indice) {
        this.valor = valor;
        this.data = data;
        this.indice = indice;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
