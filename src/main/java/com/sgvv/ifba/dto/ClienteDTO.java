package com.sgvv.ifba.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sgvv.ifba.dto.EnderecoDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome não pode ter mais de 100")
    private String nome;

    @NotNull(message = "Email inválido")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "Telefone deve ter 11 dígitos")
    private String telefone;

    @NotNull(message = "CNH é obrigatória")
    @Pattern(regexp = "\\d{11}", message = "CNH deve ter 11 dígitos")
    private String cnh;

    @NotNull(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;

    @Valid
    @NotNull(message = "Endereço é obrigatório")
    private EnderecoDTO endereco;

    
}
