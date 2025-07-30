package com.sgvv.ifba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgvv.ifba.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
