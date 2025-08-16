package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.VeiculoDTO;
import com.sgvv.ifba.model.Veiculo;
import com.sgvv.ifba.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para VeiculoServiceImpl")
class VeiculoServiceImplTest {

    // Injeta mocks na classe que está sendo testada
    @InjectMocks
    private VeiculoServiceImpl veiculoService;

    // Cria um mock do VeiculoRepository
    @Mock
    private VeiculoRepository veiculoRepository;

    private Veiculo veiculo;
    private VeiculoDTO veiculoDTO;

    // Configuração inicial antes de cada teste
    @BeforeEach
    void setup() {
        veiculo = new Veiculo(
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
    @DisplayName("Deve salvar um veículo com sucesso")
    void deveSalvarVeiculoComSucesso() {
        // Simula o comportamento do repository.save
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        VeiculoDTO resultado = veiculoService.salvar(veiculoDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ABC1D23", resultado.getPlaca());

        // Verifica se o método save foi chamado uma vez
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }

    @Test
    @DisplayName("Deve buscar um veículo por ID com sucesso")
    void deveBuscarVeiculoPorIdComSucesso() {
        // Simula o findById para retornar um Optional com o veículo
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<VeiculoDTO> resultado = veiculoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    @DisplayName("Deve retornar Optional vazio ao buscar um veículo inexistente")
    void deveRetornarOptionalVazioAoBuscarInexistente() {
        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<VeiculoDTO> resultado = veiculoService.buscarPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    @DisplayName("Deve atualizar um veículo existente com sucesso")
    void deveAtualizarVeiculoComSucesso() {
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
        Veiculo veiculoAtualizado = new Veiculo(
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

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculoAtualizado);

        Optional<VeiculoDTO> resultado = veiculoService.atualizarPorId(1L, dtoAtualizado);

        assertTrue(resultado.isPresent());
        assertEquals("Ford", resultado.get().getMarca());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }

    @Test
    @DisplayName("Deve retornar Optional vazio ao tentar atualizar um veículo inexistente")
    void deveRetornarOptionalVazioAoAtualizarInexistente() {
        VeiculoDTO dtoAtualizado = new VeiculoDTO(
                99L,
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

        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<VeiculoDTO> resultado = veiculoService.atualizarPorId(99L, dtoAtualizado);

        assertFalse(resultado.isPresent());
        verify(veiculoRepository, never()).save(any(Veiculo.class));
    }

    @Test
    @DisplayName("Deve deletar um veículo existente com sucesso")
    void deveDeletarVeiculoComSucesso() {
        when(veiculoRepository.existsById(1L)).thenReturn(true);

        boolean deletado = veiculoService.deletar(1L);

        assertTrue(deletado);
        verify(veiculoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Não deve deletar um veículo inexistente")
    void naoDeveDeletarVeiculoInexistente() {
        when(veiculoRepository.existsById(99L)).thenReturn(false);

        boolean deletado = veiculoService.deletar(99L);

        assertFalse(deletado);
        verify(veiculoRepository, never()).deleteById(anyLong());
    }
}
