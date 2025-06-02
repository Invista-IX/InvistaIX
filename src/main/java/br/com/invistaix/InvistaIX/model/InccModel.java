package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incc")
public class InccModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIncc")
    private Integer idIncc;

    @Column(name = "porcentagem")
    private Double porcentagem;

    @Column(name = "data")
    private LocalDate data;

    public InccModel() {}

    public InccModel(Double porcentagem, LocalDate data) {
        this.porcentagem = porcentagem;
        this.data = data;
    }

    public Integer getIdIncc() {
        return idIncc;
    }

    public void setIdIncc(Integer idIncc) {
        this.idIncc = idIncc;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
