package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacao")
public class AvaliacaoModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idavaliacao")
        private Long idavaliacao;

        @Column(name = "valorAvaliacao", nullable = false)
        private Double valorAvaliacao;

        @Column(name = "cnpj", nullable = false, length = 14)
        private String cnpj;

        @Column(name = "idimovel", nullable = false)
        private Long idimovel;

        @Column(name = "dataAvaliacao", nullable = false)
        private LocalDate dataAvaliacao;

        @Column(name = "razaoSocial", nullable = false, length = 130)
        private String razaoSocial;

        @Column(columnDefinition = "TEXT", nullable = false)
        private String docAvaliacao;

        @Transient
        private MultipartFile docAvaliacaoFile;

    public AvaliacaoModel() {
        }

        public AvaliacaoModel(Long idavaliacao, double valorAvaliacao, String cnpj, Long idimovel, LocalDate dataAvaliacao, String razaoSocial, String docAvaliacao, MultipartFile docAvaliacaoFile){
            this.idavaliacao = idavaliacao;
            this.valorAvaliacao = valorAvaliacao;
            this.cnpj = cnpj;
            this.idimovel = idimovel;
            this.dataAvaliacao= dataAvaliacao;
            this.razaoSocial= razaoSocial;
            this.docAvaliacao = docAvaliacao;
            this.docAvaliacaoFile = docAvaliacaoFile;
        }

    public Long getIdavaliacao() {
        return idavaliacao;
    }

    public void setIdavaliacao(Long idavaliacao) {
        this.idavaliacao = idavaliacao;
    }

    public Double getValorAvaliacao() {
        return valorAvaliacao;
    }

    public void setValorAvaliacao(Double valorAvaliacao) {
        this.valorAvaliacao = valorAvaliacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getIdimovel() {
        return idimovel;
    }

    public void setIdimovel(Long idimovel) {
        this.idimovel = idimovel;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getDocAvaliacao() {
        return docAvaliacao;
    }

    public void setDocAvaliacao(String docAvaliacao) {
        this.docAvaliacao = docAvaliacao;
    }

    public MultipartFile getDocAvaliacaoFile() {
        return docAvaliacaoFile;
    }

    public void setDocAvaliacaoFile(MultipartFile docAvaliacaoFile) {
        this.docAvaliacaoFile = docAvaliacaoFile;
    }

}


