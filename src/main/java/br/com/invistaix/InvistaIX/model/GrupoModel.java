package br.com.invistaix.InvistaIX.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name= "grupo")
@NamedQuery(name = "GrupoModel.findByCodigo", query = "select g from GrupoModel g where g.codigo = ?1")
public class GrupoModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupo")
    private Integer id;
    
    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 45)
    private String codigo;

    @Column(nullable = false, length = 45)
    private String senha;
    
    @Lob
    @Column(nullable = true)
    private byte[] imagem_base64;

	public GrupoModel(Integer id, String nome, String codigo, String senha, byte[] imagem_base64) {
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
		this.senha = senha;
		this.imagem_base64 = imagem_base64;
	}

	public GrupoModel() {
		
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
		String base64Data = Base64.getEncoder().encodeToString(imagem_base64);
        System.out.println(base64Data);
		return base64Data;
	}

	public void setImagem_base64(MultipartFile imagem_base64) {
		byte[] cpy;
		try {
			cpy = imagem_base64.getBytes();
			this.imagem_base64 = cpy;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.imagem_base64 = null;
		}
		
	}

	@Override
	public String toString() {
		return "GrupoModel [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", senha=" + senha
				+ ", imagem_base64=" + Arrays.toString(imagem_base64) + "]";
	}
    
}
