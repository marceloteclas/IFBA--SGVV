package com.sgvv.ifba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgvv.ifba.controller.CargoController;
import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.service.CargoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CargoController.class)
class CargoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CargoService cargoService;

    @Autowired
    private ObjectMapper objectMapper;

    private CargoDTO cargoDTO;

    @BeforeEach
    void setUp() {
        cargoDTO = new CargoDTO(1L, "Gerente");
    }

    @Test
    void deveRetornar201QuandoSalvarComSucesso() throws Exception {
        Mockito.when(cargoService.salvar(any(CargoDTO.class))).thenReturn(cargoDTO);

        mockMvc.perform(post("/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cargoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomeCargo").value("Gerente"));
    }

    @Test
    void deveRetornar400QuandoCargoDuplicado() throws Exception {
        Mockito.when(cargoService.salvar(any(CargoDTO.class)))
                .thenThrow(new IllegalArgumentException("Já existe um cargo com esse nome."));

        mockMvc.perform(post("/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cargoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Já existe um cargo com esse nome."));
    }

    @Test
    void deveRetornar204QuandoDeletarComSucesso() throws Exception {
        Mockito.when(cargoService.deletar(1L)).thenReturn(true);

        mockMvc.perform(delete("/cargo/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404QuandoDeletarInexistente() throws Exception {
        Mockito.when(cargoService.deletar(1L)).thenReturn(false);

        mockMvc.perform(delete("/cargo/1"))
                .andExpect(status().isNotFound());
    }
}