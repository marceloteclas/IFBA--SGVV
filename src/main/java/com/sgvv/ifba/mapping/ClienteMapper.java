package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;
import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Cliente;
import com.sgvv.ifba.model.Endereco;


@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDTO dto);
    ClienteDTO toDto(Cliente entity);
    
    Endereco toEntity(EnderecoDTO dto);
    EnderecoDTO toDto(Endereco entity);
}