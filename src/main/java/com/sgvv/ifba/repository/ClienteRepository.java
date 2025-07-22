package com.sgvv.ifba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sgvv.ifba.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long id);
    List<Cliente> findByNome(String nome);
    List<Cliente> findByCpf(String cpf);
}
