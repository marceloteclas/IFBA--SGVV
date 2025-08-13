package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Cliente;
import com.sgvv.ifba.model.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-12T21:18:19-0300",
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
        cliente.setEndereco( enderecoDTOToEndereco( dto.getEndereco() ) );
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
        clienteDTO.setEndereco( enderecoToEnderecoDTO( entity.getEndereco() ) );
        clienteDTO.setId( entity.getId() );
        clienteDTO.setNome( entity.getNome() );
        clienteDTO.setTelefone( entity.getTelefone() );

        return clienteDTO;
    }

    protected Endereco enderecoDTOToEndereco(EnderecoDTO enderecoDTO) {
        if ( enderecoDTO == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setBairro( enderecoDTO.getBairro() );
        endereco.setCidade( enderecoDTO.getCidade() );
        endereco.setEstado( enderecoDTO.getEstado() );
        endereco.setId( enderecoDTO.getId() );
        endereco.setIdentificacaoResidencial( enderecoDTO.getIdentificacaoResidencial() );
        endereco.setLogradouro( enderecoDTO.getLogradouro() );

        return endereco;
    }

    protected EnderecoDTO enderecoToEnderecoDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setBairro( endereco.getBairro() );
        enderecoDTO.setCidade( endereco.getCidade() );
        enderecoDTO.setEstado( endereco.getEstado() );
        enderecoDTO.setId( endereco.getId() );
        enderecoDTO.setIdentificacaoResidencial( endereco.getIdentificacaoResidencial() );
        enderecoDTO.setLogradouro( endereco.getLogradouro() );

        return enderecoDTO;
    }
}
