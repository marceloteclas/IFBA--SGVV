package com.sgvv.ifba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgvv.ifba.model.NivelAcesso;
import java.util.List;

@Repository
public class NivelAcessoRepository extends JpaRepository<NivelAcessoRepository, Long> {

     List<NivelAcesso> findByNome(String nome);
    
}
