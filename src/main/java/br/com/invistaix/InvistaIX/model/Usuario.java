package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gestor")
@NamedQuery(name = "Usuario.findByEmail", query = "select u from Usuario u where u.email = ?1")
@NamedQuery(name = "Usuario.findByTelefone", query = "select u from Usuario u where u.telefone = ?1")
@NamedQuery(name = "Usuario.findByCPF", query = "select u from Usuario u where u.cpfCnpj = ?1")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgestor")
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 45)
    private String sobrenome;

    @Column(length = 100)
    private String email;

    @Column(length = 16)
    private String telefone;

    @Column(name = "CNPJ_CPF", nullable = false, length = 14)
    private String cpfCnpj;

    @Column(nullable = false, length = 45)
    private String senha;

    @Column(name = "PJ", nullable = false)
    private Boolean tipoPessoa;
    
    public Usuario(Integer id, String nome, String sobrenome, String email, String telefone, String cpfCnpj, String senha, Boolean tipoPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.telefone = telefone;
		this.cpfCnpj = cpfCnpj;
		this.senha = senha;
		this.tipoPessoa = tipoPessoa;
	}
    
    public Usuario() {
    	
    }

	public Integer getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Boolean tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
}
