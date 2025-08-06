package com.sgvv.ifba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

   private Long id;

   @NotBlank(message = "Nome é obrigatório")
   @Size(max = 100, message = "Nome não pode ter mais de 100")
   private String nome;

   @NotNull(message = "Login inválido")
   @NotBlank(message = "Login é obrigatório")
   private String login;

   @NotNull(message = "Não pode ser vazio")
   @Size(min = 6, max = 50)
   private String senha;

   @NotNull
   private boolean ativo = true;

   private EnderecoDTO endereco;
   private CargoDTO cargo;
   private NivelAcessoDTO nivelAcesso;

}