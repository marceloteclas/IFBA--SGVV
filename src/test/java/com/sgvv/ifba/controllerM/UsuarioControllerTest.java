package com.sgvv.ifba.controllerM;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgvv.ifba.controller.UsuarioController;
import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.dto.UsuarioDTO;
import com.sgvv.ifba.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private ObjectMapper objectMapper = new ObjectMapper();
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNome("Maria");
        usuarioDTO.setLogin("maria123");
        usuarioDTO.setSenha("abcdef");
        usuarioDTO.setAtivo(true);
    }

    @Test
    void deveSalvarUsuarioERetornar201() throws Exception {
        EnderecoDTO endereco = new EnderecoDTO(1L, "Bahia", "Varzedo", "Centro", "Rua Principal", "Casa 1");

        CargoDTO cargo = new CargoDTO();
        cargo.setId(1L);
        cargo.setNomeCargo("Gerente");

        NivelAcessoDTO nivelAcesso = new NivelAcessoDTO();
        nivelAcesso.setId(1L);
        nivelAcesso.setNivel("Administrador");

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(1L);
        usuario.setNome("João da Silva");
        usuario.setLogin("joaosilva");
        usuario.setSenha("senhaSegura123");
        usuario.setAtivo(true);
        usuario.setEndereco(endereco);
        usuario.setCargo(cargo);
        usuario.setNivelAcesso(nivelAcesso);

        when(usuarioService.salvar(any(UsuarioDTO.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João da Silva"))
                .andExpect(jsonPath("$.cargo.nomeCargo").value("Gerente"))
                .andExpect(jsonPath("$.nivelAcesso.nivel").value("Administrador"));
    }

    @Test
    void deveRetornar400QuandoDadosInvalidosAoSalvarUsuario() throws Exception {
        // Criar usuário sem campos obrigatórios
        UsuarioDTO usuarioInvalido = new UsuarioDTO();
        usuarioInvalido.setNome(""); // Nome inválido
        usuarioInvalido.setLogin(""); // Login inválido
        usuarioInvalido.setSenha("123"); // Senha curta

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveDeletarUsuarioRetornando204() throws Exception {
        Long id = 1L;
        when(usuarioService.deletar(id)).thenReturn(true);

        mockMvc.perform(delete("/usuario/{id}", id))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).deletar(id);
    }

    @Test
    void deveRetornar404QuandoUsuarioNaoEncontradoParaDelecao() throws Exception {
        Long id = 99L;
        when(usuarioService.deletar(id)).thenReturn(false);

        mockMvc.perform(delete("/usuario/{id}", id))
                .andExpect(status().isNotFound());

        verify(usuarioService, times(1)).deletar(id);
    }

}
