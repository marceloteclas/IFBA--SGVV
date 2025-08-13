package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.model.Cliente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-12T23:42:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Azul Systems, Inc.)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        return clienteDTO;
    }
}
