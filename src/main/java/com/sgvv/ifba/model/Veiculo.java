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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_VEICULO")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String renavam;

    @NotNull
    @Column(nullable = false, unique = true)
    private String placa;

    @NotNull
    @Column(nullable = false)
    private String marca;

    @NotNull
    @Column(nullable = false)
    private String modelo;

    @NotNull
    @Column(nullable = false)
    private int ano;

    @NotNull
    @Column(nullable = false, unique = true)
    private String chassi;

    private String combustivel;
    private int potencia;
    private int cilindrada;

    @NotNull
    @Column(nullable = false)
    private boolean ativo = true;
    
}
