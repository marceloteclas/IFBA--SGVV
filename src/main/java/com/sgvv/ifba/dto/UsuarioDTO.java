package com.sgvv.ifba.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Generated;

public class UsuarioDTO {

   private Long id;
   private @NotBlank(
   message = "Nome é obrigatório"
) @Size(
   max = 100,
   message = "Nome não pode ter mais de 100"
) String nome;
   private @Email(
   message = "Email inválido"
) @NotBlank(
   message = "Email é obrigatório"
) String email;
   private @NotBlank(
   message = "Telefone é obrigatório"
) @Pattern(
   regexp = "\\d{11}",
   message = "Telefone deve ter 11 dígitos"
) String telefone;
   private @NotBlank(
   message = "Data de nascimento é obrigatória"
) @Pattern(
   regexp = "\\d{4}-\\d{2}-\\d{2}",
   message = "Formato de data inválido. Use yyyy-MM-dd"
) String dataNascimento;
   private @NotBlank(
   message = "CPF é obrigatório"
) @Pattern(
   regexp = "\\d{11}",
   message = "CPF deve ter 11 dígitos"
) String cpf;
   private EspecialidadeDTO especialidade;

   @Generated
   public Long getId() {
      return this.id;
   }

   @Generated
   public String getNome() {
      return this.nome;
   }

   @Generated
   public String getEmail() {
      return this.email;
   }

   @Generated
   public String getTelefone() {
      return this.telefone;
   }

   @Generated
   public String getDataNascimento() {
      return this.dataNascimento;
   }

   @Generated
   public String getCpf() {
      return this.cpf;
   }

   @Generated
   public EspecialidadeDTO getEspecialidade() {
      return this.especialidade;
   }

   @Generated
   public void setId(final Long id) {
      this.id = id;
   }

   @Generated
   public void setNome(final String nome) {
      this.nome = nome;
   }

   @Generated
   public void setEmail(final String email) {
      this.email = email;
   }

   @Generated
   public void setTelefone(final String telefone) {
      this.telefone = telefone;
   }

   @Generated
   public void setDataNascimento(final String dataNascimento) {
      this.dataNascimento = dataNascimento;
   }

   @Generated
   public void setCpf(final String cpf) {
      this.cpf = cpf;
   }

   @Generated
   public void setEspecialidade(final EspecialidadeDTO especialidade) {
      this.especialidade = especialidade;
   }

   @Generated
   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ProfissionalDTO)) {
         return false;
      } else {
         ProfissionalDTO other = (ProfissionalDTO)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label95: {
               Object this$id = this.getId();
               Object other$id = other.getId();
               if (this$id == null) {
                  if (other$id == null) {
                     break label95;
                  }
               } else if (this$id.equals(other$id)) {
                  break label95;
               }

               return false;
            }

            Object this$nome = this.getNome();
            Object other$nome = other.getNome();
            if (this$nome == null) {
               if (other$nome != null) {
                  return false;
               }
            } else if (!this$nome.equals(other$nome)) {
               return false;
            }

            Object this$email = this.getEmail();
            Object other$email = other.getEmail();
            if (this$email == null) {
               if (other$email != null) {
                  return false;
               }
            } else if (!this$email.equals(other$email)) {
               return false;
            }

            label74: {
               Object this$telefone = this.getTelefone();
               Object other$telefone = other.getTelefone();
               if (this$telefone == null) {
                  if (other$telefone == null) {
                     break label74;
                  }
               } else if (this$telefone.equals(other$telefone)) {
                  break label74;
               }

               return false;
            }

            label67: {
               Object this$dataNascimento = this.getDataNascimento();
               Object other$dataNascimento = other.getDataNascimento();
               if (this$dataNascimento == null) {
                  if (other$dataNascimento == null) {
                     break label67;
                  }
               } else if (this$dataNascimento.equals(other$dataNascimento)) {
                  break label67;
               }

               return false;
            }

            Object this$cpf = this.getCpf();
            Object other$cpf = other.getCpf();
            if (this$cpf == null) {
               if (other$cpf != null) {
                  return false;
               }
            } else if (!this$cpf.equals(other$cpf)) {
               return false;
            }

            Object this$especialidade = this.getEspecialidade();
            Object other$especialidade = other.getEspecialidade();
            if (this$especialidade == null) {
               if (other$especialidade != null) {
                  return false;
               }
            } else if (!this$especialidade.equals(other$especialidade)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(final Object other) {
      return other instanceof ProfissionalDTO;
   }

   @Generated
   public int hashCode() {
      int PRIME = true;
      int result = 1;
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $nome = this.getNome();
      result = result * 59 + ($nome == null ? 43 : $nome.hashCode());
      Object $email = this.getEmail();
      result = result * 59 + ($email == null ? 43 : $email.hashCode());
      Object $telefone = this.getTelefone();
      result = result * 59 + ($telefone == null ? 43 : $telefone.hashCode());
      Object $dataNascimento = this.getDataNascimento();
      result = result * 59 + ($dataNascimento == null ? 43 : $dataNascimento.hashCode());
      Object $cpf = this.getCpf();
      result = result * 59 + ($cpf == null ? 43 : $cpf.hashCode());
      Object $especialidade = this.getEspecialidade();
      result = result * 59 + ($especialidade == null ? 43 : $especialidade.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = String.valueOf(this.getId());
      return "ProfissionalDTO(id=" + var10000 + ", nome=" + this.getNome() + ", email=" + this.getEmail() + ", telefone=" + this.getTelefone() + ", dataNascimento=" + this.getDataNascimento() + ", cpf=" + this.getCpf() + ", especialidade=" + String.valueOf(this.getEspecialidade()) + ")";
   }

   @Generated
   public ProfissionalDTO(final Long id, final String nome, final String email, final String telefone, final String dataNascimento, final String cpf, final EspecialidadeDTO especialidade) {
      this.id = id;
      this.nome = nome;
      this.email = email;
      this.telefone = telefone;
      this.dataNascimento = dataNascimento;
      this.cpf = cpf;
      this.especialidade = especialidade;
   }

   @Generated
   public ProfissionalDTO() {
   }
}


