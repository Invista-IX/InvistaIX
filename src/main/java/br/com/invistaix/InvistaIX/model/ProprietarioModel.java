package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proprietario")
public class ProprietarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    
    @Column(name = "sobrenome", nullable = false, length = 45)
    private String sobrenome;
    
    @Column(name = "pessoa_FJ", nullable = false, length = 1)
    private Character tipoPessoa;
    
    @Column(name = "CNPJ_CPF", nullable = false, length = 14)
    private String cnpjCpf;
    
    @Column(name = "email", length = 45)
    private String email;
    
    @Column(name = "telefone", length = 16)
    private String telefone;
    
    public ProprietarioModel(Integer id, String nome, String sobrenome, Character tipoPessoa, String cnpjCpf, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipoPessoa = tipoPessoa;
        this.cnpjCpf = cnpjCpf;
        this.email = email;
        this.telefone = telefone;
    }

    public ProprietarioModel() {
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public Character getTipoPessoa() {
    	return tipoPessoa;
    }
    
    public void setTipoPessoa(Character tipoPessoa) {
    	this.tipoPessoa = tipoPessoa;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

