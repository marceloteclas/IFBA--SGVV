package com.sgvv.ifba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelAcessoDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatório")
    @Size(max = 50, message = "Descrição não pode ter mais de 50 caracteres")
    private String nivel;

}