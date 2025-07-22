package com.sgvv.ifba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 50, message = "Cidade não pode ter mais de 50 caracteres") 
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 50, message = "Estado não pode ter mais de 50 caracteres")
    private String estado;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 50, message = "Bairro não pode ter mais de 50 caracteres")
    private String bairro;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 100, message = "Logradouro não pode ter mais de 100 caracteres")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número não pode ter mais de 10 caracteres")
    private String numero;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos")
    private String cep;
}
