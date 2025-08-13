package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.UsuarioDTO;
import com.sgvv.ifba.mapping.UsuarioMapper;
import com.sgvv.ifba.model.Usuario;
import com.sgvv.ifba.repository.UsuarioRepository;
import com.sgvv.ifba.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario entity = usuarioMapper.toEntity(usuarioDTO);
        Usuario saved = usuarioRepository.save(entity);
        return usuarioMapper.toDto(saved);
    }

    @Override
    public boolean deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;

    }

}
