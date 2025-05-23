package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "iptu")
public class ImpostoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idiptu")
    private int idiptu;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "idimovel")
    private Long idimovel;

    public ImpostoModel() {
    }

    public ImpostoModel(int idiptu, double valor, LocalDate data, Long idimovel){
        this.idiptu = idiptu;
        this.valor = valor;
        this.data = data;
        this.idimovel = idimovel;
    }

    public int getIdiptu() {
        return idiptu;
    }

    public void setIdiptu(int idiptu) {
        this.idiptu = idiptu;
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

    public Long getIdimovel() {
        return idimovel;
    }

    public void setIdimovel(Long idimovel) {
        this.idimovel = idimovel;
    }
}
