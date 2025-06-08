package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ir")
public class IRModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIR;

    private Double valor;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "idimovel")
    private ImovelModel imovel;

    public Long getIdIR() {
        return idIR;
    }

    public void setIdIR(Long idIR) {
        this.idIR = idIR;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ImovelModel getImovel() {
        return imovel;
    }

    public void setImovel(ImovelModel imovel) {
        this.imovel = imovel;
    }
}
