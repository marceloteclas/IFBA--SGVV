package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.model.Cargo;
import com.sgvv.ifba.repository.CargoRepository;
import com.sgvv.ifba.service.impl.CargoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CargoServiceImplTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoServiceImpl cargoService;

    private CargoDTO cargoDTO;
    private Cargo cargo;

    @BeforeEach
    void setUp() {
        cargoDTO = new CargoDTO(null, "Gerente");
        cargo = new Cargo(1L, "Gerente");
    }

    @Test
    void deveCriarCargoComSucesso() {
        // Simula que não existe cargo com o mesmo nome
        when(cargoRepository.existsByNomeCargo("Gerente")).thenReturn(false);
        when(cargoRepository.save(any(Cargo.class))).thenReturn(cargo);

        CargoDTO salvo = cargoService.salvar(cargoDTO);

        assertNotNull(salvo.getId());
        assertEquals("Gerente", salvo.getNomeCargo());
        verify(cargoRepository, times(1)).save(any(Cargo.class));
    }

    @Test
    void deveLancarExcecaoQuandoNomeCargoDuplicado() {
        when(cargoRepository.existsByNomeCargo("Gerente")).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> cargoService.salvar(cargoDTO));

        assertEquals("Já existe um cargo com esse nome.", ex.getMessage());
        verify(cargoRepository, never()).save(any(Cargo.class));
    }

    @Test
    void deveDeletarCargoExistente() {
        when(cargoRepository.existsById(1L)).thenReturn(true);

        boolean resultado = cargoService.deletar(1L);

        assertTrue(resultado);
        verify(cargoRepository, times(1)).deleteById(1L);
    }

    @Test
    void naoDeveDeletarCargoInexistente() {
        when(cargoRepository.existsById(1L)).thenReturn(false);

        boolean resultado = cargoService.deletar(1L);

        assertFalse(resultado);
        verify(cargoRepository, never()).deleteById(anyLong());
    }
}