package com.sgvv.ifba.service;

import com.sgvv.ifba.dto.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO salvar(UsuarioDTO usuarioDTO);

    boolean deletar(Long id);

}
