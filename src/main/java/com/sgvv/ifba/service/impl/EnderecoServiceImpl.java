package com.sgvv.ifba.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Endereco;
import com.sgvv.ifba.repository.EnderecoRepository;
import com.sgvv.ifba.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Override
    @Transactional // Garante que a operação seja transacional (commit ou rollback)
    public EnderecoDTO salvar(EnderecoDTO enderecoDTO) {
        // Mapeia o DTO para a entidade
        Endereco endereco = new Endereco();
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setIdentificacaoResidencial(enderecoDTO.getIdentificacaoResidencial());

        // Salva a entidade no banco de dados
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        
        // Mapeia a entidade salva de volta para um DTO e retorna
        enderecoDTO.setId(enderecoSalvo.getId()); // Atualiza o DTO com o ID gerado
        return enderecoDTO;
    }

    @Override
    @Transactional(readOnly = true) // Transação somente leitura, otimizada para consultas
    public Optional<EnderecoDTO> buscarPorId(Long id) { // Modificado para receber Long id e retornar Optional
        // Busca a entidade pelo ID
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        
        // Se encontrar, mapeia para DTO e retorna. Caso contrário, retorna Optional vazio.
        return enderecoOptional.map(endereco -> {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setId(endereco.getId());
            enderecoDTO.setEstado(endereco.getEstado());
            enderecoDTO.setCidade(endereco.getCidade());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setLogradouro(endereco.getLogradouro());
            enderecoDTO.setIdentificacaoResidencial(endereco.getIdentificacaoResidencial());
            return enderecoDTO;
        });
    }

    @Override
    @Transactional
    public Optional<EnderecoDTO> atualizarPorId(Long id, EnderecoDTO enderecoDTO) { // Modificado para receber Long id
        // Verifica se o endereço existe
        return enderecoRepository.findById(id).map(enderecoExistente -> {
            // Atualiza os campos da entidade existente com os dados do DTO
            enderecoExistente.setEstado(enderecoDTO.getEstado());
            enderecoExistente.setCidade(enderecoDTO.getCidade());
            enderecoExistente.setBairro(enderecoDTO.getBairro());
            enderecoExistente.setLogradouro(enderecoDTO.getLogradouro());
            enderecoExistente.setIdentificacaoResidencial(enderecoDTO.getIdentificacaoResidencial());

            // Salva as alterações (o método save do JPA atualiza se o ID já existe)
            Endereco enderecoAtualizado = enderecoRepository.save(enderecoExistente);

            // Mapeia a entidade atualizada de volta para DTO e retorna
            enderecoDTO.setId(enderecoAtualizado.getId()); // Garante que o ID correto está no DTO de retorno
            return enderecoDTO;
        });
        // Se o findById não encontrar, o map não será executado e um Optional vazio será retornado.
    }

    @Override
    @Transactional
    public boolean deletar(Long id) { // Modificado para receber Long id e retornar boolean
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return true; // Retorna true se a exclusão foi bem-sucedida
        }
        return false; // Retorna false se o ID não foi encontrado
    }
    
}
