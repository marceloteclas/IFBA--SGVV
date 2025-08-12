package com.sgvv.ifba.service.impl;

import java.util.List;

import com.sgvv.ifba.dto.UsuarioDTO;
import com.sgvv.ifba.mapping.CargoMapper;
import com.sgvv.ifba.mapping.EnderecoMapper;
import com.sgvv.ifba.mapping.NivelAcessoMapper;
import com.sgvv.ifba.mapping.UsuarioMapper;

import com.sgvv.ifba.model.Usuario;
import com.sgvv.ifba.repository.UsuarioRepository;
import com.sgvv.ifba.service.UsuarioService;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoMapper enderecoMapper;
    private final CargoMapper cargoMapper;
    private final NivelAcessoMapper nivelAcessoMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
            EnderecoMapper enderecoMapper,
            CargoMapper cargoMapper,
            NivelAcessoMapper nivelAcessoMapper) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoMapper = enderecoMapper;
        this.cargoMapper = cargoMapper;
        this.nivelAcessoMapper = nivelAcessoMapper;
    }

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setAtivo(usuarioDTO.isAtivo());

        if (usuarioDTO.getEndereco() != null) {
            usuario.setEndereco(enderecoMapper.toEntity(usuarioDTO.getEndereco()));
        }
        if (usuarioDTO.getCargo() != null) {
            usuario.setCargo(cargoMapper.toEntity(usuarioDTO.getCargo()));
        }
        if (usuarioDTO.getNivelAcesso() != null) {
            usuario.setNivelAcesso(nivelAcessoMapper.toEntity(usuarioDTO.getNivelAcesso()));
        }

        Usuario savedUsuario = usuarioRepository.save(usuario);

        usuarioDTO.setId(savedUsuario.getId());
        return usuarioDTO;
    }

    @Override
    public boolean deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;

    }

    // @Override
    // public List<UsuarioDTO> listarUsuarios() {
    // return usuarioRepository.findAll()
    // .stream()
    // .map(usuarioMapper::toDto)
    // .toList();
    // .map(usuarioMapper::toDto)
    // .toList();

    // }

}
