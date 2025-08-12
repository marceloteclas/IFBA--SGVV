package com.sgvv.ifba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O RENAVAM é obrigatório")
    @Size(min = 11, max = 11, message = "O RENAVAM deve conter 11 dígitos")
    @Pattern(regexp = "\\d+", message = "O RENAVAM deve conter apenas números")
    //@NotNull
    @Column(nullable = false, unique = true, length = 11)
    private String renavam;

    @NotBlank(message = "A placa é obrigatória")
    @Size(min = 7, max = 7, message = "A placa deve conter 7 caracteres")
    @Pattern(regexp = "[A-Z]{3}\\d[A-Z]\\d{2}|[A-Z]{3}\\d{4}", message = "Formato de placa inválido")
    //@NotNull
    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    @NotBlank(message = "A marca é obrigatória")
    @Size(max = 50, message = "A marca deve ter no máximo 50 caracteres")
    //@NotNull
    @Column(nullable = false, length = 50)
    private String marca;

    @NotBlank(message = "O modelo é obrigatório")
    @Size(max = 50, message = "O modelo deve ter no máximo 50 caracteres")
    //@NotNull
    @Column(nullable = false, length = 50)
    private String modelo;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "O ano deve ser posterior a 1900")
    @Max(value = 2100, message = "O ano deve ser anterior a 2100")
    //@NotNull
    @Column(nullable = false)
    private int ano;

    @NotBlank(message = "O chassi é obrigatório")
    @Size(min = 17, max = 17, message = "O chassi deve conter 17 caracteres")
    //@NotNull
    @Column(nullable = false, unique = true, length = 17)
    private String chassi;

    @Size(max = 20, message = "O tipo de combustível deve ter no máximo 20 caracteres")
    @Column(length = 20)
    private String combustivel;

    @Min(value = 1, message = "A potência deve ser maior que zero")
    @Column
    private int potencia;

    @Min(value = 1, message = "A cilindrada deve ser maior que zero")
    @Column
    private int cilindrada;

    @NotNull(message = "O status de ativo é obrigatório")
    //@NotNull
    @Column(nullable = false)
    private boolean ativo = true;
    
}