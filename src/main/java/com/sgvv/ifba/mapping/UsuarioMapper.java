package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;

import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.dto.UsuarioDTO;
import com.sgvv.ifba.model.Cargo;
import com.sgvv.ifba.model.Endereco;
import com.sgvv.ifba.model.NivelAcesso;
import com.sgvv.ifba.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDto(Usuario usuario);

    Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDto(Endereco entity);

    Cargo toEntity(CargoDTO cargodto);

    CargoDTO toDto(Cargo cargoentity);

    NivelAcesso toEntity(NivelAcessoDTO niveldto);

    NivelAcessoDTO toDto(NivelAcesso nivelAcessoentity);
}
