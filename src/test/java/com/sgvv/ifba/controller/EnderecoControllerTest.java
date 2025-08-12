package com.sgvv.ifba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnderecoController.class)
@DisplayName("Testes para EnderecoController")
class EnderecoControllerTest {

    // MockMvc é usado para simular requisições HTTP para o controlador
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper é usado para converter objetos Java para JSON e vice-versa
    @Autowired
    private ObjectMapper objectMapper;

    // @MockBean cria um mock do EnderecoService e o injeta no contexto do Spring
    @MockBean
    private EnderecoService enderecoService;

    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setup() {
        enderecoDTO = new EnderecoDTO(
                1L,
                "Bahia",
                "Salvador",
                "Barra",
                "Avenida Oceânica",
                "Apto 101"
        );
    }

    @Test
    @DisplayName("Deve salvar um endereço e retornar 201 Created")
    void deveSalvarEnderecoEretornarStatus201() throws Exception {
        // Simula o comportamento do serviço quando o método salvar é chamado
        when(enderecoService.salvar(any(EnderecoDTO.class))).thenReturn(enderecoDTO);

        // Converte o DTO para JSON
        String enderecoJson = objectMapper.writeValueAsString(enderecoDTO);

        // Realiza a requisição POST e verifica o resultado
        mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enderecoJson))
                .andExpect(status().isCreated()) // Espera um status 201 Created
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.estado").value("Bahia"));

        // Verifica se o método salvar do serviço foi chamado
        verify(enderecoService, times(1)).salvar(any(EnderecoDTO.class));
    }

    @Test
    @DisplayName("Deve buscar um endereço por ID e retornar 200 OK")
    void deveBuscarEnderecoPorIdEretornarStatus200() throws Exception {
        when(enderecoService.buscarPorId(1L)).thenReturn(Optional.of(enderecoDTO));

        mockMvc.perform(get("/enderecos/{id}", 1L))
                .andExpect(status().isOk()) // Espera um status 200 OK
                .andExpect(jsonPath("$.id").value(1L));

        verify(enderecoService, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao buscar um ID inexistente")
    void deveRetornar404AoBuscarIdInexistente() throws Exception {
        when(enderecoService.buscarPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/enderecos/{id}", 99L))
                .andExpect(status().isNotFound()); // Espera um status 404 Not Found

        verify(enderecoService, times(1)).buscarPorId(99L);
    }

    @Test
    @DisplayName("Deve atualizar um endereço e retornar 200 OK")
    void deveAtualizarEnderecoEretornarStatus200() throws Exception {
        EnderecoDTO dtoAtualizado = new EnderecoDTO(
                1L,
                "São Paulo",
                "São Paulo",
                "Centro",
                "Rua da Consolação",
                "Casa 55"
        );
        when(enderecoService.atualizarPorId(eq(1L), any(EnderecoDTO.class))).thenReturn(Optional.of(dtoAtualizado));

        String enderecoAtualizadoJson = objectMapper.writeValueAsString(dtoAtualizado);

        mockMvc.perform(put("/enderecos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enderecoAtualizadoJson))
                .andExpect(status().isOk()) // Espera um status 200 OK
                .andExpect(jsonPath("$.estado").value("São Paulo"));

        verify(enderecoService, times(1)).atualizarPorId(eq(1L), any(EnderecoDTO.class));
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao tentar atualizar um ID inexistente")
    void deveRetornar404AoAtualizarIdInexistente() throws Exception {
        when(enderecoService.atualizarPorId(eq(99L), any(EnderecoDTO.class))).thenReturn(Optional.empty());
        String enderecoJson = objectMapper.writeValueAsString(enderecoDTO);

        mockMvc.perform(put("/enderecos/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enderecoJson))
                .andExpect(status().isNotFound()); // Espera um status 404 Not Found

        verify(enderecoService, times(1)).atualizarPorId(eq(99L), any(EnderecoDTO.class));
    }

    @Test
    @DisplayName("Deve deletar um endereço e retornar 204 No Content")
    void deveDeletarEnderecoEretornarStatus204() throws Exception {
        when(enderecoService.deletar(1L)).thenReturn(true);

        mockMvc.perform(delete("/enderecos/{id}", 1L))
                .andExpect(status().isNoContent()); // Espera um status 204 No Content

        verify(enderecoService, times(1)).deletar(1L);
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao tentar deletar um ID inexistente")
    void deveRetornar404AoDeletarIdInexistente() throws Exception {
        when(enderecoService.deletar(99L)).thenReturn(false);

        mockMvc.perform(delete("/enderecos/{id}", 99L))
                .andExpect(status().isNotFound()); // Espera um status 404 Not Found

        verify(enderecoService, times(1)).deletar(99L);
    }
}
