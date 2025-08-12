package com.sgvv.ifba.service;

import java.util.List;

import com.sgvv.ifba.dto.NivelAcessoDTO;

public interface NivelAcessoService {

    NivelAcessoDTO salvar(NivelAcessoDTO nivelDTO);

    boolean deletar(Long id);

    List<NivelAcessoDTO> listarNivelAcesso();

}
