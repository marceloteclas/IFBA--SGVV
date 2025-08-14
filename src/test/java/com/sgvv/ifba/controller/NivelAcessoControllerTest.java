package com.sgvv.ifba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.service.NivelAcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NivelAcessoController.class)
class NivelAcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NivelAcessoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private NivelAcessoDTO dto;

    @BeforeEach
    void setUp() {
        dto = new NivelAcessoDTO(1L, "Administrador");
    }

    @Test
    void deveRetornar201QuandoCriarComSucesso() throws Exception {
        when(service.salvar(any(NivelAcessoDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/nivelAcesso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nivel").value("Administrador"));
    }

    @Test
    void deveRetornar400QuandoDuplicado() throws Exception {
        when(service.salvar(any(NivelAcessoDTO.class)))
                .thenThrow(new IllegalArgumentException("Já existe um nível de acesso com esse nome."));

        mockMvc.perform(post("/nivelAcesso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Já existe um nível de acesso com esse nome."));
    }

    @Test
    void deveRetornar204QuandoDeletarComSucesso() throws Exception {
        when(service.deletar(1L)).thenReturn(true);

        mockMvc.perform(delete("/nivelAcesso/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404QuandoDeletarInexistente() throws Exception {
        when(service.deletar(1L)).thenReturn(false);

        mockMvc.perform(delete("/nivelAcesso/1"))
                .andExpect(status().isNotFound());
    }
}
