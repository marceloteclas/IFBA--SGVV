package com.sgvv.ifba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgvv.ifba.model.NivelAcesso;

@Repository
public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Long> {
    List<NivelAcesso> findByNivel(String nivel);

    boolean existsByNivel(String nivel);

}
