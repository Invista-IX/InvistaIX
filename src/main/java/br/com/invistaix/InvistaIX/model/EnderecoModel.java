package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Long id;

    @Column(name = "bairro", nullable = false, length = 45)
    private String bairro;

    @Column(name = "loteamento", nullable = false, length = 45)
    private String loteamento;

    @Column(name = "municipio", nullable = false, length = 45)
    private String municipio;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "rua", nullable = false, length = 45)
    private String rua;

    public EnderecoModel(Long id, String bairro, String municipio, Integer numero, String rua, String loteamento) {
        this.id = id;
        this.bairro = bairro;
        this.municipio = municipio;
        this.numero = numero;
        this.rua = rua;
        this.loteamento = loteamento;
    }

    public EnderecoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLoteamento() {
        return loteamento;
    }

    public void setLoteamento(String loteamento) {
        this.loteamento = loteamento;
    }
}
