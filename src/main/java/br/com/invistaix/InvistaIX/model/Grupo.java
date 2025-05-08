package br.com.invistaix.InvistaIX.model;

import jakarta.persistence.*;

@Entity
@Table(name= "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgrupo")
    private Integer id;

    @Column(nullable = false, length = 45)
    private String codigo;

    @Column(nullable = false, length = 45)
    private String senha;

    @Column(nullable = false, length = 45)
    private String imagem_base64;
}
