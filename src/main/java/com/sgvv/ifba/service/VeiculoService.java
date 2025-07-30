package com.sgvv.ifba.service;

import java.util.Optional;

import com.sgvv.ifba.dto.VeiculoDTO;

public interface VeiculoService {

    VeiculoDTO salvar(VeiculoDTO veiculoDTO);

    Optional<VeiculoDTO> buscarPorId(Long id);

    Optional<VeiculoDTO> atualizarPorId(Long id, VeiculoDTO veiculoDTO);

    boolean deletar(Long id);

}
