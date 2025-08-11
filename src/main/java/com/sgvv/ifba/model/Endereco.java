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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data // Gera getter, setter, toString, equals e hashCode
@AllArgsConstructor // Cria um construtor que recebe como parâmetros todos os atributos da classe
@NoArgsConstructor // Cria um construtor padrão que não recebe nenhum argumento
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O Estado não pode ser vazio")
    @Size(max = 50, message = "O Estado deve ter no máximo 50 caracteres")
    //@NotNull // Faz a validação no service
    @Column(nullable = false, length = 19) // Cria esse campo no banco de dados como NOT NULL
    private String estado;

    @NotBlank(message = "A cidade não pode ser vazia")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres")
    //@NotNull
    @Column(nullable = false, length = 100)
    private String cidade;

    @NotBlank(message = "O bairro não pode ser vazio")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres")
    //@NotNull
    @Column(nullable = false, length = 100)
    private String bairro;

    @NotBlank(message = "O logradouro não pode ser vazio")
    @Size(max = 200, message = "O logradouro deve ter no máximo 200 caracteres")
    //@NotNull
    @Column(nullable = false, length = 200)
    private String logradouro;

    @NotBlank(message = "A identificação residencial não pode ser vazia")
    @Size(max = 100, message = "A identificação residencial deve ter no máximo 100 caracteres")
    //@NotNull
    @Column(nullable = false, length = 100)
    private String identificacaoResidencial;

}
