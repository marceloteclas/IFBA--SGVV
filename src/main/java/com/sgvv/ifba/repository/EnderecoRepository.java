package com.sgvv.ifba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sgvv.ifba.entity.Endereco;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCep(String cep);
}
