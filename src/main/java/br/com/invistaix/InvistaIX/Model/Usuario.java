package br.com.invistaix.InvistaIX.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String cpfCnpj;
    private String telefone;
    private String tipoPessoa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
}
