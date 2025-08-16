package com.sgvv.ifba.cliente;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.mapping.ClienteMapper;
import com.sgvv.ifba.model.Cliente;
import com.sgvv.ifba.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.sgvv.ifba.service.impl.ClienteServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private ClienteDTO clienteDTO;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNome("Fulano de Tal");
        clienteDTO.setEmail("fulano@example.com");
        clienteDTO.setTelefone("71999999999");
        clienteDTO.setCnh("12345678901");
        clienteDTO.setCpf("12345678901");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Fulano de Tal");
        cliente.setEmail("fulano@example.com");
        cliente.setTelefone("71999999999");
        cliente.setCnh("12345678901");
        cliente.setCpf("12345678901");
    }

    @Test
    void salvar_deveRetornarClienteDTOQuandoSalvarComSucesso() {
        when(clienteMapper.toEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.salvar(clienteDTO);

        assertEquals(clienteDTO, result);
        verify(clienteMapper, times(1)).toEntity(clienteDTO);
        verify(clienteRepository, times(1)).save(cliente);
        verify(clienteMapper, times(1)).toDto(cliente);
    }

    @Test
    void atualizarPorId_deveRetornarOptionalVazioQuandoClienteNaoExistir() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ClienteDTO> result = clienteService.atualizarPorId(1L, clienteDTO);

        assertTrue(result.isEmpty());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void atualizarPorId_deveRetornarClienteAtualizadoQuandoExistir() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDTO);

        Optional<ClienteDTO> result = clienteService.atualizarPorId(1L, clienteDTO);

        assertTrue(result.isPresent());
        assertEquals(clienteDTO, result.get());
        verify(clienteRepository, times(1)).findById(1L);
        verify(clienteMapper, times(1)).toEntity(clienteDTO);
        verify(clienteRepository, times(1)).save(cliente);
        verify(clienteMapper, times(1)).toDto(cliente);
    }

    @Test
    void deletar_deveRetornarFalseQuandoClienteNaoExistir() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        boolean result = clienteService.deletar(1L);

        assertFalse(result);
        verify(clienteRepository, times(1)).existsById(1L);
        verify(clienteRepository, never()).deleteById(1L);
    }

    @Test
    void deletar_deveRetornarTrueQuandoClienteExistir() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        boolean result = clienteService.deletar(1L);

        assertTrue(result);
        verify(clienteRepository, times(1)).existsById(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void listarClientes_deveRetornarListaDeClientesDTO() {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDTO);

        List<ClienteDTO> result = clienteService.listarClientes();

        assertEquals(1, result.size());
        assertEquals(clienteDTO, result.get(0));
        verify(clienteRepository, times(1)).findAll();
        verify(clienteMapper, times(1)).toDto(cliente);
    }

    @Test
    void buscarClientePorCpf_deveRetornarListaDeClientesDTO() {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findByCpf("12345678901")).thenReturn(clientes);
        when(clienteMapper.toDto(cliente)).thenReturn(clienteDTO);

        List<ClienteDTO> result = clienteService.buscarClientePorCpf("12345678901");

        assertEquals(1, result.size());
        assertEquals(clienteDTO, result.get(0));
        verify(clienteRepository, times(1)).findByCpf("12345678901");
        verify(clienteMapper, times(1)).toDto(cliente);
    }
}