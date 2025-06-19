package br.com.invistaix.InvistaIX.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "gestor")
@NamedQuery(name = "Usuario.findByEmail", query = "select u from UsuarioModel u where u.email = ?1")
@NamedQuery(name = "Usuario.findByTelefone", query = "select u from UsuarioModel u where u.telefone = ?1")
@NamedQuery(name = "Usuario.findByCPF", query = "select u from UsuarioModel u where u.cpfCnpj = ?1")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgestor")
    private Long id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 45)
    private String sobrenome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 16)
    private String telefone;

    @Column(name = "CNPJ_CPF", nullable = false, length = 14)
    private String cpfCnpj;

    @Column(nullable = false, length = 45)
    private String senha;

    @Column(name = "pessoa_fj", nullable = false, length = 1)
    private Character tipoPessoa;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "gestor_x_grupo",
    			joinColumns = @JoinColumn(
    								name = "idgestor",
    								referencedColumnName = "idgestor"
    			),
    			inverseJoinColumns = @JoinColumn(
    								name = "idgrupo",
    								referencedColumnName = "idgrupo"
    			)
    )
    private Set<GrupoModel> grupos = new HashSet<>();
    
    public UsuarioModel(Long id, String nome, String sobrenome, String email, String telefone, String cpfCnpj, String senha, Character tipoPessoa) {
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
    
    public UsuarioModel() {
    	
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Character getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Character tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
	public Set<GrupoModel> getGrupos() {
		return grupos;
	}
    
	public void setGrupos(Set<GrupoModel> grupos) {
		this.grupos = grupos;
	}
	
	public void adicionarGrupo(GrupoModel grupo) {
		grupos.add(grupo);
	}
	
	public void removerGrupo(GrupoModel grupo) {
		grupos.remove(grupo);
	}

	@Override
	public String toString() {
		ArrayList<String> idGrupos = new ArrayList<String>();
		for (GrupoModel grupo: grupos) {
			idGrupos.add(Long.toString(grupo.getId()));
		};
		return "UsuarioModel [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", telefone=" + telefone + ", cpfCnpj=" + cpfCnpj + ", senha=" + senha + ", tipoPessoa=" + tipoPessoa
				+ ", grupos=" + idGrupos.toString()  + "]";
	}
}
