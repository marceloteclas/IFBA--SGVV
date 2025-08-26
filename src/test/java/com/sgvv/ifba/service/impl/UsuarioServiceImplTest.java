package com.sgvv.ifba.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sgvv.ifba.dto.UsuarioDTO;
import com.sgvv.ifba.mapping.UsuarioMapper;
import com.sgvv.ifba.model.Usuario;
import com.sgvv.ifba.repository.UsuarioRepository;
import com.sgvv.ifba.service.impl.UsuarioServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioDTO usuarioDTO;
    private Usuario usuarioEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNome("João");
        usuarioDTO.setLogin("joao123");
        usuarioDTO.setSenha("123456");
        usuarioDTO.setAtivo(true);

        usuarioEntity = new Usuario();
        usuarioEntity.setId(1L);
        usuarioEntity.setNome("João");
        usuarioEntity.setLogin("joao123");
        usuarioEntity.setSenha("123456");
        usuarioEntity.setAtivo(true);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        when(usuarioRepository.existsByLogin("joao123")).thenReturn(false);
        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(usuarioEntity);
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioMapper.toDto(usuarioEntity)).thenReturn(usuarioDTO);

        UsuarioDTO salvo = usuarioService.salvar(usuarioDTO);

        assertNotNull(salvo);
        assertEquals("joao123", salvo.getLogin());
        verify(usuarioRepository).save(usuarioEntity);
    }

    @Test
    void deveLancarExcecaoQuandoLoginDuplicado() {
        when(usuarioRepository.existsByLogin("joao123")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.salvar(usuarioDTO));

        assertEquals("Já existe um usuário com este login: joao123", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveDeletarUsuarioExistente() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        boolean deletado = usuarioService.deletar(1L);

        assertTrue(deletado);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void naoDeveDeletarUsuarioInexistente() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        boolean deletado = usuarioService.deletar(1L);

        assertFalse(deletado);
        verify(usuarioRepository, never()).deleteById(any());
    }
}
