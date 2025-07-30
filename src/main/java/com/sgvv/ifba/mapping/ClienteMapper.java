package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;
import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.model.Cliente;


@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDTO dto);
    ClienteDTO toDto(Cliente entity);
}
