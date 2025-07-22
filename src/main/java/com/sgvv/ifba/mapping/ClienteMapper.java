package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;
import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteDTO clienteDTO);
    ClienteDTO toDTO(Cliente cliente);
}
