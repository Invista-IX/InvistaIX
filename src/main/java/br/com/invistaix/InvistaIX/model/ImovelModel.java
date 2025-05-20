package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

@Entity
public class ImovelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Double valorMatricula;
    private String numeroMatricula;
    private String imagemBase64;

    @ManyToOne
    private ProprietarioModel proprietarioModel;

    @ManyToOne
    private EnderecoModel enderecoModel;

    public ImovelModel(Integer id, String nome, Double valorMatricula, String numeroMatricula, String imagemBase64, ProprietarioModel proprietarioModel, EnderecoModel enderecoModel) {
      this.id = id;
      this.nome = nome;
      this.valorMatricula = valorMatricula;
      this.numeroMatricula = numeroMatricula;
      this.imagemBase64 = imagemBase64;
      this.proprietarioModel = proprietarioModel;
      this.enderecoModel = enderecoModel;
    }

    public ImovelModel() {
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

    public ProprietarioModel getProprietarioModel() {
        return proprietarioModel;
    }

    public void setProprietarioModel(ProprietarioModel proprietarioModel) {
        this.proprietarioModel = proprietarioModel;
    }

    public EnderecoModel getEnderecoModel() {
        return enderecoModel;
    }

    public void setEnderecoModel(EnderecoModel enderecoModel) {
        this.enderecoModel = enderecoModel;
    }
}
