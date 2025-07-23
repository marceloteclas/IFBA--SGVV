package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;
import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.entity.Cliente;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {
    public abstract Cliente toEntity(ClienteDTO dto);
    public abstract ClienteDTO toDto(Cliente entity);
}
