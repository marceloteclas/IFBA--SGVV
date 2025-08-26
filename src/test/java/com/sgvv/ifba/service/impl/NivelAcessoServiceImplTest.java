package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.model.NivelAcesso;
import com.sgvv.ifba.repository.NivelAcessoRepository;
import com.sgvv.ifba.service.impl.NivelAcessoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NivelAcessoServiceImplTest {

    @Mock
    private NivelAcessoRepository repository;

    @InjectMocks
    private NivelAcessoServiceImpl service;

    private NivelAcessoDTO dto;
    private NivelAcesso entity;

    @BeforeEach
    void setUp() {
        dto = new NivelAcessoDTO(null, "Administrador");
        entity = new NivelAcesso(1L, "Administrador");
    }

    @Test
    void deveCriarNivelComSucesso() {
        when(repository.existsByNivel("Administrador")).thenReturn(false);
        when(repository.save(any(NivelAcesso.class))).thenReturn(entity);

        NivelAcessoDTO salvo = service.salvar(dto);

        assertNotNull(salvo.getId());
        assertEquals("Administrador", salvo.getNivel());
        verify(repository).save(any(NivelAcesso.class));
    }

    @Test
    void deveLancarExcecaoQuandoDuplicado() {
        when(repository.existsByNivel("Administrador")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.salvar(dto));
        assertEquals("Já existe um nível de acesso com esse nome.", ex.getMessage());
        verify(repository, never()).save(any(NivelAcesso.class));
    }

    @Test
    void deveDeletarNivelExistente() {
        when(repository.existsById(1L)).thenReturn(true);

        boolean resultado = service.deletar(1L);

        assertTrue(resultado);
        verify(repository).deleteById(1L);
    }

    @Test
    void naoDeveDeletarNivelInexistente() {
        when(repository.existsById(1L)).thenReturn(false);

        boolean resultado = service.deletar(1L);

        assertFalse(resultado);
        verify(repository, never()).deleteById(anyLong());
    }
}