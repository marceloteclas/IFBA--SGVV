package com.sgvv.ifba.service;

import com.sgvv.ifba.dto.ClienteDTO;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDTO salvar(ClienteDTO clienteDTO);
    Optional<ClienteDTO> atualizarPorId(Long id, ClienteDTO clienteDTO);
    boolean deletar(Long id);
    List<ClienteDTO> listarClientes();
    List<ClienteDTO> buscarClientePorCpf(String cpf);
}
