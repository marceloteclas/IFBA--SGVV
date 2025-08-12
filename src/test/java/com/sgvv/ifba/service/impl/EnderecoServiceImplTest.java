package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.model.Endereco;
import com.sgvv.ifba.repository.EnderecoRepository;
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
@DisplayName("Testes para EnderecoServiceImpl")
class EnderecoServiceImplTest {

    // @InjectMocks injeta os mocks na classe que está sendo testada
    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    // @Mock cria um mock do EnderecoRepository
    @Mock
    private EnderecoRepository enderecoRepository;

    private Endereco endereco;
    private EnderecoDTO enderecoDTO;

    // Método executado antes de cada teste para inicializar objetos
    @BeforeEach
    void setup() {
        endereco = new Endereco(
                1L,
                "Bahia",
                "Salvador",
                "Barra",
                "Avenida Oceânica",
                "Apto 101"
        );

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
    @DisplayName("Deve salvar um endereço com sucesso")
    void deveSalvarEnderecoComSucesso() {
        // Simula o comportamento do repository.save quando é chamado
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        // Chama o método que está sendo testado
        EnderecoDTO resultado = enderecoService.salvar(enderecoDTO);

        // Verifica se o resultado não é nulo e se o ID foi atribuído
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Bahia", resultado.getEstado());

        // Verifica se o método save do repository foi chamado uma vez
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    @DisplayName("Deve buscar um endereço por ID com sucesso")
    void deveBuscarEnderecoPorIdComSucesso() {
        // Simula o comportamento do findById para retornar um Optional com o endereço
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        // Chama o método do serviço
        Optional<EnderecoDTO> resultado = enderecoService.buscarPorId(1L);

        // Verifica se o Optional não está vazio e se o ID está correto
        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    @DisplayName("Deve retornar Optional vazio ao buscar um endereço inexistente")
    void deveRetornarOptionalVazioAoBuscarInexistente() {
        // Simula o comportamento do findById para retornar um Optional vazio
        when(enderecoRepository.findById(99L)).thenReturn(Optional.empty());

        // Chama o método do serviço
        Optional<EnderecoDTO> resultado = enderecoService.buscarPorId(99L);

        // Verifica se o Optional está vazio
        assertFalse(resultado.isPresent());
    }

    @Test
    @DisplayName("Deve atualizar um endereço existente com sucesso")
    void deveAtualizarEnderecoComSucesso() {
        EnderecoDTO dtoAtualizado = new EnderecoDTO(
                1L,
                "São Paulo",
                "São Paulo",
                "Centro",
                "Rua da Consolação",
                "Casa 55"
        );
        Endereco enderecoAtualizado = new Endereco(
                1L,
                "São Paulo",
                "São Paulo",
                "Centro",
                "Rua da Consolação",
                "Casa 55"
        );

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoAtualizado);

        Optional<EnderecoDTO> resultado = enderecoService.atualizarPorId(1L, dtoAtualizado);

        assertTrue(resultado.isPresent());
        assertEquals("São Paulo", resultado.get().getEstado());
        verify(enderecoRepository, times(1)).findById(1L);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    @DisplayName("Deve retornar Optional vazio ao tentar atualizar um endereço inexistente")
    void deveRetornarOptionalVazioAoAtualizarInexistente() {
        EnderecoDTO dtoAtualizado = new EnderecoDTO(
                99L,
                "São Paulo",
                "São Paulo",
                "Centro",
                "Rua da Consolação",
                "Casa 55"
        );

        when(enderecoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<EnderecoDTO> resultado = enderecoService.atualizarPorId(99L, dtoAtualizado);

        assertFalse(resultado.isPresent());
        verify(enderecoRepository, never()).save(any(Endereco.class));
    }

    @Test
    @DisplayName("Deve deletar um endereço existente com sucesso")
    void deveDeletarEnderecoComSucesso() {
        // Simula o comportamento do existsById para retornar true
        when(enderecoRepository.existsById(1L)).thenReturn(true);

        // Chama o método do serviço
        boolean deletado = enderecoService.deletar(1L);

        // Verifica se o retorno é true
        assertTrue(deletado);
        // Verifica se o método deleteById foi chamado uma vez
        verify(enderecoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Não deve deletar um endereço inexistente")
    void naoDeveDeletarEnderecoInexistente() {
        // Simula o comportamento do existsById para retornar false
        when(enderecoRepository.existsById(99L)).thenReturn(false);

        // Chama o método do serviço
        boolean deletado = enderecoService.deletar(99L);

        // Verifica se o retorno é false
        assertFalse(deletado);
        // Verifica se o método deleteById nunca foi chamado
        verify(enderecoRepository, never()).deleteById(anyLong());
    }
}