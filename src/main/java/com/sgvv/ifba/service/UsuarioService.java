package com.sgvv.ifba.service;

import java.util.List;

import com.sgvv.ifba.dto.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO salvar(UsuarioDTO usuarioDTO);

    boolean deletar(Long id);

    // List<UsuarioDTO> listarUsuarios();

}
