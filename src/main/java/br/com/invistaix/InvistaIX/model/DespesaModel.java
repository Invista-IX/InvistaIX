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

    @Column(name = "manutencao", nullable = true)
    private Double manutencao;

    @Column(name = "despesaavulsa", nullable = true)
    private Double despesaAvulsa;

    @Column(name = "luz", nullable = true)
    private Double luz;

    @Column(name = "agua", nullable = true)
    private Double agua;

    @Column(name = "data", nullable = false)
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
