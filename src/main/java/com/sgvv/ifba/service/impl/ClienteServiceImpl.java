package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.model.Cliente;
import com.sgvv.ifba.mapping.ClienteMapper;
import com.sgvv.ifba.repository.ClienteRepository;
import com.sgvv.ifba.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Cliente entity = clienteMapper.toEntity(clienteDTO);
        Cliente saved = clienteRepository.save(entity);
        return clienteMapper.toDto(saved);
    }

    @Override
    public Optional<ClienteDTO> atualizarPorId(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    Cliente atualizado = clienteMapper.toEntity(clienteDTO);
                    atualizado.setId(clienteExistente.getId());
                    Cliente saved = clienteRepository.save(atualizado);
                    return clienteMapper.toDto(saved);
                });
    }

    @Override
    public boolean deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    public List<ClienteDTO> buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }
}
