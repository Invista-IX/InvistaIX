package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "despesa")
public class DespesaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddespesa")
    private Long id;

    @Column(name = "manutencao")
    private Double manutencao;

    @Column(name = "despesaavulsa")
    private Double despesaAvulsa;

    @Column(name = "luz")
    private Double luz;

    @Column(name = "agua")
    private Double agua;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "idimovel", nullable = false)
    private Long idImovel;

    public DespesaModel() {
    }

    public DespesaModel(Double manutencao, Double despesaAvulsa, LocalDate data, Long idImovel) {
        this.manutencao = manutencao;
        this.despesaAvulsa = despesaAvulsa;
        this.data = data;
        this.idImovel = idImovel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getManutencao() {
        return manutencao;
    }

    public void setManutencao(Double manutencao) {
        this.manutencao = manutencao;
    }

    public Double getDespesaAvulsa() {
        return despesaAvulsa;
    }

    public void setDespesaAvulsa(Double despesaAvulsa) {
        this.despesaAvulsa = despesaAvulsa;
    }

    public Double getLuz() {
        return luz;
    }

    public void setLuz(Double luz) {
        this.luz = luz;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getAgua() {
        return agua;
    }

    public void setAgua(Double agua) {
        this.agua = agua;
    }

    public Long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Long idImovel) {
        this.idImovel = idImovel;
    }
}
