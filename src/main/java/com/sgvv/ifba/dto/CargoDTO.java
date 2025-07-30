package com.sgvv.ifba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Generated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome não pode ter mais de 100")
    private String nome;

  
}
