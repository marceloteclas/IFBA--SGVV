package com.sgvv.ifba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Gera getter, setter, toString, equals e hashCode
@AllArgsConstructor // Cria um construtor que recebe como parâmetros todos os atributos da classe
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull // Faz a validação no service
    @Column(nullable = false) // Cria esse campo no banco de dados como NOT NULL
    private String estado;

    @NotNull
    @Column(nullable = false)
    private String cidade;

    @NotNull
    @Column(nullable = false)
    private String bairro;

    @NotNull
    @Column(nullable = false)
    private String logradouro;

    @NotNull
    @Column(nullable = false)
    private String identificacaoResidencial;

}
