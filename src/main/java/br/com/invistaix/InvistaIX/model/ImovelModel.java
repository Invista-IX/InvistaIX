package br.com.invistaix.InvistaIX.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "imovel")
public class ImovelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimovel")
    private Long id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "numero_matricula", nullable = false)
    private String numeroMatricula;

    @Column(name = "valor_matricula", nullable = false)
    private Double valorMatricula;

    @Lob
    @Column(name = "imagem_base64", nullable = true)
    private byte[] imagemBase64;

    @Column(name = "idproprietario", nullable = false)
    private Long idProprietario;

    @Column(name = "idgrupo", nullable = false)
    private Long idGrupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idendereco", nullable = false)
    private EnderecoModel endereco;

    public ImovelModel() {
    }

    public ImovelModel(Long id, String nome, LocalDateTime dataCadastro, Double area, Double preco,
                       Long idProprietario, Long idGrupo, EnderecoModel endereco, byte[] imagemBase64, Double valorMatricula) {
        this.id = id;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.area = area;
        this.preco = preco;
        this.idProprietario = idProprietario;
        this.idGrupo = idGrupo;
        this.endereco = endereco;
        this.imagemBase64 = imagemBase64;
        this.valorMatricula = valorMatricula;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public Double getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(Double valorMatricula) {
        this.valorMatricula = valorMatricula;
    }

    public byte[] getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(byte[] imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoFormatado() {
        if (endereco == null) {
            return "Endereço não disponível";
        }

        return String.format(
                "%s, %d - %s, %s - %s - %se",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getLoteamento(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCEP()
        );
    }

}