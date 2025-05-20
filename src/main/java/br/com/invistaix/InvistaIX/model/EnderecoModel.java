package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

@Entity
public class EnderecoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Double valorMatricula;
    private String numeroMatricula;
    private String imagemBase64;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private ProprietarioModel proprietario;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private EnderecoModel endereco;

    public EnderecoModel(Integer id, String nome, Double valorMatricula, String numeroMatricula, String imagemBase64, ProprietarioModel proprietario, EnderecoModel endereco) {
        this.id = id;
        this.nome = nome;
        this.valorMatricula = valorMatricula;
        this.numeroMatricula = numeroMatricula;
        this.imagemBase64 = imagemBase64;
        this.proprietario = proprietario;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(Double valorMatricula) {
        this.valorMatricula = valorMatricula;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

    public ProprietarioModel getProprietario() {
        return proprietario;
    }

    public void setProprietario(ProprietarioModel proprietario) {
        this.proprietario = proprietario;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}

