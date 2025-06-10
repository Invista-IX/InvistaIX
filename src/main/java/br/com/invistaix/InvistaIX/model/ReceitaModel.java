package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Entity
@Table(name = "receita")
public class ReceitaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreceita")
    private Long id;

    @Column(name = "aluguel", nullable = true)
    private Double aluguel;

    @Column(name = "receitaavulsa", nullable = true)
    private Double receitaAvulsa;

    @Column(name = "data", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(name = "idimovel", nullable = false)
    private Long idImovel;

    public ReceitaModel() {
    }

    public ReceitaModel(Double aluguel, Double receitaAvulsa, LocalDate data, Long idImovel) {
        this.aluguel = aluguel;
        this.receitaAvulsa = receitaAvulsa;
        this.data = data;
        this.idImovel = idImovel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAluguel() {
        return aluguel;
    }

    public void setAluguel(Double aluguel) {
        this.aluguel = aluguel;
    }

    public Double getReceitaAvulsa() {
        return receitaAvulsa;
    }

    public void setReceitaAvulsa(Double receitaAvulsa) {
        this.receitaAvulsa = receitaAvulsa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Long idImovel) {
        this.idImovel = idImovel;
    }
}
