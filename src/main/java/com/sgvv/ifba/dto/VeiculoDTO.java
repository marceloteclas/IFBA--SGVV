package com.sgvv.ifba.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera getters, setters, toString, equals e hashCode
@AllArgsConstructor // Cria um construtor com todos os argumentos
@NoArgsConstructor // Cria um construtor sem argumentos
public class VeiculoDTO {

    private Long id;

    @NotNull
    private String renavam;

    @NotNull
    private String placa;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private int ano;

    @NotNull
    private String chassi;

    private String combustivel;
    private int potencia;
    private int cilindrada;

    @NotNull
    private boolean ativo = true;
    
}
