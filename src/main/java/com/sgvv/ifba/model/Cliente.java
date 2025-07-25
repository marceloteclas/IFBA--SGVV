package com.sgvv.ifba.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String telefone;

    @NotNull
    @Column(nullable = false, unique = true)
    private String cnh;

    @NotNull
    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
