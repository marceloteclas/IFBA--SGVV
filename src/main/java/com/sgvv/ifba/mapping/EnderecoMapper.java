package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;

import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDTO(Endereco entity);

}
