package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incc")
public class InccModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idincc")
    private Long idIncc;

    @Column(name = "porcentagem", nullable = false)
    private Double porcentagem;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    public InccModel() {}

    public InccModel(Double porcentagem, LocalDate data) {
        this.porcentagem = porcentagem;
        this.data = data;
    }

    public Long getIdIncc() {
        return idIncc;
    }

    public void setIdIncc(Long idIncc) {
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
