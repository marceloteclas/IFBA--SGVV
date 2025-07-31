package com.sgvv.ifba.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nivel_acesso")
public class NivelAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome; // Exemplo: "ADMIN", "USUARIO", "GERENTE", etc.

    @ManyToMany(mappedBy = "niveisAcesso")
    private Set<Usuario> usuarios;
}
