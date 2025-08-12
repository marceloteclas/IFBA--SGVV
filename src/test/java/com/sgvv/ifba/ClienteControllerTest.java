package com.sgvv.ifba;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sgvv.ifba.controller.ClienteController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNome("Fulano de Tal");
        clienteDTO.setEmail("fulano@example.com");
        clienteDTO.setTelefone("71999999999");
        clienteDTO.setCnh("12345678901");
        clienteDTO.setCpf("12345678901");
    }

    @Test
    void salvar_deveRetornarClienteSalvoComStatusCreated() {
        when(clienteService.salvar(any(ClienteDTO.class))).thenReturn(clienteDTO);
        
        ResponseEntity<ClienteDTO> response = clienteController.salvar(clienteDTO);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
        verify(clienteService, times(1)).salvar(clienteDTO);
    }

    @Test
    void listarTodos_deveRetornarListaDeClientes() {
        List<ClienteDTO> clientes = Arrays.asList(clienteDTO);
        when(clienteService.listarClientes()).thenReturn(clientes);
        
        ResponseEntity<List<ClienteDTO>> response = clienteController.listarTodos();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
        verify(clienteService, times(1)).listarClientes();
    }

    @Test
    void buscarPorCpf_deveRetornarClientesEncontrados() {
        List<ClienteDTO> clientes = Arrays.asList(clienteDTO);
        when(clienteService.buscarClientePorCpf("12345678901")).thenReturn(clientes);
        
        ResponseEntity<List<ClienteDTO>> response = clienteController.buscarPorCpf("12345678901");
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
        verify(clienteService, times(1)).buscarClientePorCpf("12345678901");
    }

    @Test
    void atualizar_deveRetornarClienteAtualizadoQuandoExistir() {
        when(clienteService.atualizarPorId(1L, clienteDTO)).thenReturn(Optional.of(clienteDTO));
        
        ResponseEntity<ClienteDTO> response = clienteController.atualizar(1L, clienteDTO);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDTO, response.getBody());
        verify(clienteService, times(1)).atualizarPorId(1L, clienteDTO);
    }

    @Test
    void atualizar_deveRetornarNotFoundQuandoNaoExistir() {
        when(clienteService.atualizarPorId(1L, clienteDTO)).thenReturn(Optional.empty());
        
        ResponseEntity<ClienteDTO> response = clienteController.atualizar(1L, clienteDTO);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteService, times(1)).atualizarPorId(1L, clienteDTO);
    }

    @Test
    void deletar_deveRetornarNoContentQuandoDeletar() {
        when(clienteService.deletar(1L)).thenReturn(true);
        
        ResponseEntity<Void> response = clienteController.deletar(1L);
        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteService, times(1)).deletar(1L);
    }

    @Test
    void deletar_deveRetornarNotFoundQuandoNaoDeletar() {
        when(clienteService.deletar(1L)).thenReturn(false);
        
        ResponseEntity<Void> response = clienteController.deletar(1L);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteService, times(1)).deletar(1L);
    }
}