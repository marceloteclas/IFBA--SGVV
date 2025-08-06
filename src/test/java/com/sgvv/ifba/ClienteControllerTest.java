package com.sgvv.ifba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteDTO clienteMock() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("João");
        dto.setCpf("12345678900");
        dto.setEmail("joao@email.com");
        dto.setTelefone("11999999999");
        dto.setCnh("98765432100");
        return dto;
    }

    @Test
    void testSalvar() throws Exception {
        ClienteDTO dto = clienteMock();

        Mockito.when(clienteService.salvar(Mockito.any(ClienteDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    void testListarTodos() throws Exception {
        ClienteDTO dto = clienteMock();

        Mockito.when(clienteService.listarClientes()).thenReturn(List.of(dto));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"));
    }

    @Test
    void testBuscarPorCpf() throws Exception {
        ClienteDTO dto = clienteMock();

        Mockito.when(clienteService.buscarClientePorCpf("12345678900")).thenReturn(List.of(dto));

        mockMvc.perform(get("/clientes/cpf/12345678900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cpf").value("12345678900"));
    }

    @Test
    void testAtualizarEncontrado() throws Exception {
        ClienteDTO dto = clienteMock();

        Mockito.when(clienteService.atualizarPorId(Mockito.eq(1L), Mockito.any())).thenReturn(Optional.of(dto));

        mockMvc.perform(put("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    void testAtualizarNaoEncontrado() throws Exception {
        ClienteDTO dto = clienteMock();

        Mockito.when(clienteService.atualizarPorId(Mockito.eq(1L), Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletarSucesso() throws Exception {
        Mockito.when(clienteService.deletar(1L)).thenReturn(true);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletarNaoEncontrado() throws Exception {
        Mockito.when(clienteService.deletar(1L)).thenReturn(false);

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNotFound());
    }
    
}
