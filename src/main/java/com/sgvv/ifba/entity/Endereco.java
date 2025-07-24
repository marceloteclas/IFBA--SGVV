package com.sgvv.ifba.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;
    private String estado;
    private String bairro;
    private String logradouro;
    private String numero;
    private String cep;

    public Endereco() {}

    public Endereco(String cidade, String estado, String bairro, String logradouro, String numero, String cep) {
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }


}