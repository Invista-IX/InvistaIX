package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Integer id;

    @Column(name = "rua", nullable = false, length = 45)
    private String rua;
    
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "loteamento", nullable = false, length = 45)
    private String loteamento;

    @Column(name = "cidade", nullable = false, length = 45)
    private String cidade;
    
    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "CEP", nullable = false, length = 8)
    private String CEP;
    
    public EnderecoModel(Integer id, String rua, Integer numero, String loteamento, String cidade, String estado, String CEP) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.loteamento = loteamento;
        this.cidade = cidade;
        this.estado = estado;
        this.CEP = CEP;

    }

    public EnderecoModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public String getLoteamento() {
        return loteamento;
    }

    public void setLoteamento(String loteamento) {
        this.loteamento = loteamento;
    }
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCEP() {
		return CEP;
	}
	
	public void setCEP(String CEP) {
		this.CEP = CEP;
	}
}
