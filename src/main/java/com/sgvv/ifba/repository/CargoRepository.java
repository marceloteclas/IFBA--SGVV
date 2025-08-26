package com.sgvv.ifba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sgvv.ifba.model.Cargo;
import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    List<Cargo> findBynomeCargo(String nomeCargo);

    boolean existsByNomeCargo(String nomeCargo);

}