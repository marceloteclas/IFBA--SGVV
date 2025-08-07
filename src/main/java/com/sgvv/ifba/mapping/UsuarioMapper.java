package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;
import com.sgvv.ifba.dto.UsuarioDTO;
import com.sgvv.ifba.model.Usuario;

@Mapper(componentModel = "spring", uses = { NivelAcessoMapper.class })
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDto(Usuario usuario);
}
