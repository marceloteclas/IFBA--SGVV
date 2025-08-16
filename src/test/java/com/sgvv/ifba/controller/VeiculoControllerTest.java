package com.sgvv.ifba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgvv.ifba.dto.VeiculoDTO;
import com.sgvv.ifba.service.VeiculoService;
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

@WebMvcTest(VeiculoController.class)
@DisplayName("Testes para VeiculoController")
class VeiculoControllerTest {

    // MockMvc simula requisições HTTP
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper converte objetos Java para JSON
    @Autowired
    private ObjectMapper objectMapper;

    // Mocka o VeiculoService no contexto do Spring
    @MockBean
    private VeiculoService veiculoService;

    private VeiculoDTO veiculoDTO;

    @BeforeEach
    void setup() {
        veiculoDTO = new VeiculoDTO(
                1L,
                "12345678901",
                "ABC1D23",
                "Volkswagen",
                "Gol",
                2020,
                "9BWXXXXXXXXXXXXXX",
                "Flex",
                100,
                1600,
                true
        );
    }

    @Test
    @DisplayName("Deve salvar um veículo e retornar 201 Created")
    void deveSalvarVeiculoEretornarStatus201() throws Exception {
        when(veiculoService.salvar(any(VeiculoDTO.class))).thenReturn(veiculoDTO);

        String veiculoJson = objectMapper.writeValueAsString(veiculoDTO);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.placa").value("ABC1D23"));

        verify(veiculoService, times(1)).salvar(any(VeiculoDTO.class));
    }

    @Test
    @DisplayName("Deve buscar um veículo por ID e retornar 200 OK")
    void deveBuscarVeiculoPorIdEretornarStatus200() throws Exception {
        when(veiculoService.buscarPorId(1L)).thenReturn(Optional.of(veiculoDTO));

        mockMvc.perform(get("/veiculos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(veiculoService, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao buscar ID inexistente")
    void deveRetornar404AoBuscarIdInexistente() throws Exception {
        when(veiculoService.buscarPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/veiculos/{id}", 99L))
                .andExpect(status().isNotFound());

        verify(veiculoService, times(1)).buscarPorId(99L);
    }

    @Test
    @DisplayName("Deve atualizar um veículo e retornar 200 OK")
    void deveAtualizarVeiculoEretornarStatus200() throws Exception {
        VeiculoDTO dtoAtualizado = new VeiculoDTO(
                1L,
                "11111111111",
                "DEF4G56",
                "Ford",
                "Ka",
                2022,
                "9BWYYYYYYYYYYYYYY",
                "Gasolina",
                80,
                1000,
                true
        );
        when(veiculoService.atualizarPorId(eq(1L), any(VeiculoDTO.class))).thenReturn(Optional.of(dtoAtualizado));

        String veiculoAtualizadoJson = objectMapper.writeValueAsString(dtoAtualizado);

        mockMvc.perform(put("/veiculos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoAtualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Ford"));

        verify(veiculoService, times(1)).atualizarPorId(eq(1L), any(VeiculoDTO.class));
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao tentar atualizar um ID inexistente")
    void deveRetornar404AoAtualizarIdInexistente() throws Exception {
        when(veiculoService.atualizarPorId(eq(99L), any(VeiculoDTO.class))).thenReturn(Optional.empty());
        String veiculoJson = objectMapper.writeValueAsString(veiculoDTO);

        mockMvc.perform(put("/veiculos/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isNotFound());

        verify(veiculoService, times(1)).atualizarPorId(eq(99L), any(VeiculoDTO.class));
    }

    @Test
    @DisplayName("Deve deletar um veículo e retornar 204 No Content")
    void deveDeletarVeiculoEretornarStatus204() throws Exception {
        when(veiculoService.deletar(1L)).thenReturn(true);

        mockMvc.perform(delete("/veiculos/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(veiculoService, times(1)).deletar(1L);
    }

    @Test
    @DisplayName("Deve retornar 404 Not Found ao tentar deletar um ID inexistente")
    void deveRetornar404AoDeletarIdInexistente() throws Exception {
        when(veiculoService.deletar(99L)).thenReturn(false);

        mockMvc.perform(delete("/veiculos/{id}", 99L))
                .andExpect(status().isNotFound());

        verify(veiculoService, times(1)).deletar(99L);
    }
}
