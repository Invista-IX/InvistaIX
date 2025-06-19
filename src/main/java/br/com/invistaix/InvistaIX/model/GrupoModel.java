package br.com.invistaix.InvistaIX.model;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name= "grupo")
@NamedQuery(name = "GrupoModel.findByCodigo", query = "select g from GrupoModel g where g.codigo = ?1")
public class GrupoModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupo")
    private Long id;
    
    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 45)
    private String codigo;

    @Column(nullable = false, length = 45)
    private String senha;
    
    @Column(nullable = true)
    private String imagem_base64;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "grupos", fetch = FetchType.EAGER)
    private Set<UsuarioModel> usuarios = new HashSet<>();

	public GrupoModel(Long id, String nome, String codigo, String senha, String imagem_base64) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.senha = senha;
		this.imagem_base64 = imagem_base64;
	}

	public GrupoModel() {
		
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getImagem_base64() {
		return imagem_base64;
	}
	
	public void setImagem_base64(String imagem_base64) {
		this.imagem_base64 = imagem_base64;
	}

	public Set<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void adicionarUsuario(UsuarioModel usuario) {
		usuarios.add(usuario);
	}
	
	public void removerUsusario(UsuarioModel usuario) {
		usuarios.remove(usuario);
	}

	@Override
	public String toString() {
		ArrayList<String> idUsuarios = new ArrayList<String>();
		for(UsuarioModel usuario: usuarios) {
			idUsuarios.add(Long.toString(usuario.getId()));
		}
		return "GrupoModel [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", senha=" + senha
				+ ", imagem_base64=" + imagem_base64 + ", usuarios=" + idUsuarios.toString() + "]";
	}
    
}
