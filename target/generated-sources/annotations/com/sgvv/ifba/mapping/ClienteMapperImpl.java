package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Cliente;
import com.sgvv.ifba.model.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-16T15:57:24-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setCnh( dto.getCnh() );
        cliente.setCpf( dto.getCpf() );
        cliente.setEmail( dto.getEmail() );
        cliente.setEndereco( toEntity( dto.getEndereco() ) );
        cliente.setId( dto.getId() );
        cliente.setNome( dto.getNome() );
        cliente.setTelefone( dto.getTelefone() );

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setCnh( entity.getCnh() );
        clienteDTO.setCpf( entity.getCpf() );
        clienteDTO.setEmail( entity.getEmail() );
        clienteDTO.setEndereco( toDto( entity.getEndereco() ) );
        clienteDTO.setId( entity.getId() );
        clienteDTO.setNome( entity.getNome() );
        clienteDTO.setTelefone( entity.getTelefone() );

        return clienteDTO;
    }

    @Override
    public Endereco toEntity(EnderecoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setBairro( dto.getBairro() );
        endereco.setCidade( dto.getCidade() );
        endereco.setEstado( dto.getEstado() );
        endereco.setId( dto.getId() );
        endereco.setIdentificacaoResidencial( dto.getIdentificacaoResidencial() );
        endereco.setLogradouro( dto.getLogradouro() );

        return endereco;
    }

    @Override
    public EnderecoDTO toDto(Endereco entity) {
        if ( entity == null ) {
            return null;
        }

        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setBairro( entity.getBairro() );
        enderecoDTO.setCidade( entity.getCidade() );
        enderecoDTO.setEstado( entity.getEstado() );
        enderecoDTO.setId( entity.getId() );
        enderecoDTO.setIdentificacaoResidencial( entity.getIdentificacaoResidencial() );
        enderecoDTO.setLogradouro( entity.getLogradouro() );

        return enderecoDTO;
    }
}
