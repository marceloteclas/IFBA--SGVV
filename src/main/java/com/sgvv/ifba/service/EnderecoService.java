package com.sgvv.ifba.service;

import java.util.Optional;

import com.sgvv.ifba.dto.EnderecoDTO;

public interface EnderecoService {

    EnderecoDTO salvar(EnderecoDTO enderecoDTO);

    Optional<EnderecoDTO> buscarPorId(Long id);

    Optional<EnderecoDTO> atualizarPorId(Long id, EnderecoDTO enderecoDTO);

    boolean deletar(Long id);

}
