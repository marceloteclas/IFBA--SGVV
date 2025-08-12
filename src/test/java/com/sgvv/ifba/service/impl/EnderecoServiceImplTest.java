package com.sgvv.ifba.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sgvv.ifba.repository.EnderecoRepository;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceImplTest {

    @InjectMocks
    private EnderecoServiceImpl enderecoServiceImpl;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Test
    void testAtualizarPorId() {

    }

    @Test
    void testBuscarPorId() {
        enderecoRepository.findById(null);
    }

    @Test
    void testDeletar() {

    }

    @Test
    void testSalvar() {

    }
}
