package com.sgvv.ifba.service;

import java.util.List;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.entity.Cliente;
import com.sgvv.ifba.entity.Endereco;

public interface ClienteService {
        Cliente cadastrarCliente(Cliente cliente);
        List<Cliente> buscarClientePorCpf(String cpf);
        Cliente atualizarCliente(Cliente cliente);
        void deletarCliente(long id);
        Endereco cadastrarEndereco(Endereco endereco);
        List<Endereco> buscarEnderecoPorCep(String cep);
        Endereco atualizarEndereco(Endereco endereco);
        List<ClienteDTO> listarClientes();
        ClienteDTO salvarCliente(ClienteDTO clienteDTO);
}
