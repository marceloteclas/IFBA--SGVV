package com.sgvv.ifba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cnh;
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    private Long carroId;

    public Cliente() {}

    public Cliente(String nome, String email, String telefone, String cnh, String cpf, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnh = cnh;
        this.cpf = cpf;
        this.endereco = endereco;
    }
}
