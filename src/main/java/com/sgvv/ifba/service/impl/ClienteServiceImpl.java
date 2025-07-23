package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.entity.Cliente;
import com.sgvv.ifba.entity.Endereco;
import com.sgvv.ifba.mapping.ClienteMapper;
import com.sgvv.ifba.repository.ClienteRepository;
import com.sgvv.ifba.repository.EnderecoRepository;
import com.sgvv.ifba.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoRepository enderecoRepository;

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getEmail() == null || cliente.getTelefone() == null ||
            cliente.getCnh() == null || cliente.getCpf() == null || cliente.getEndereco() == null) {
            throw new IllegalArgumentException("Todos os campos do cliente devem ser preenchidos.");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @Override
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
        .stream()
        .map(clienteMapper::toDto)
        .toList();
    }

    @Override
    public void deletarCliente(long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public Endereco cadastrarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public List<Endereco> buscarEnderecoPorCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }

    @Override
    public Endereco atualizarEndereco(Endereco endereco) {
        Endereco entity = enderecoRepository.findById(endereco.getId())
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com o ID: " + endereco.getId()));
        entity.setLogradouro(endereco.getLogradouro());
        entity.setNumero(endereco.getNumero());
        entity.setBairro(endereco.getBairro());
        entity.setCidade(endereco.getCidade());
        entity.setEstado(endereco.getEstado());
        entity.setCep(endereco.getCep());

        return enderecoRepository.save(entity);
    }
    @Override
    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        Cliente entity = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(entity);
        return clienteMapper.toDto(savedCliente);
    }

}
