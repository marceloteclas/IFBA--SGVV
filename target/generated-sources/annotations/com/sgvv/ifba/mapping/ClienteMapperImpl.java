package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Cliente;
import com.sgvv.ifba.model.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-16T16:13:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
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

    @Override
    public Endereco toEntity(EnderecoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        return endereco;
    }

    @Override
    public EnderecoDTO toDto(Endereco entity) {
        if ( entity == null ) {
            return null;
        }

        EnderecoDTO enderecoDTO = new EnderecoDTO();

        return enderecoDTO;
    }
}
