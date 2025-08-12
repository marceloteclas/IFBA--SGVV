package com.sgvv.ifba;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.dto.EnderecoDTO;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Teste campos obrigatórios nulos
    @Test
    void quandoCamposObrigatoriosNulos_deveRetornarViolacoes() {
        ClienteDTO dto = new ClienteDTO();

        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(dto);

        // DEBUG: imprime as mensagens para ver o que está causando as violações
        violations.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));

        assertEquals(6, violations.size()); // nome, email, telefone, cnh, cpf, endereco (todos nulos)
    }

    // Teste email inválido
    @Test
    void quandoEmailInvalido_deveRetornarViolacao() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Fulano");
        dto.setEmail("email-invalido"); // email errado
        dto.setTelefone("71999999999");
        dto.setCnh("12345678901");
        dto.setCpf("12345678901");

        // endereço válido para não gerar violação aqui
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setEstado("BA");
        endereco.setCidade("Salvador");
        endereco.setBairro("Centro");
        endereco.setLogradouro("Rua Exemplo");
        endereco.setIdentificacaoResidencial("123");
        dto.setEndereco(endereco);

        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(dto);

        // Espera só 1 violação (email inválido)
        assertEquals(1, violations.size());
        assertEquals("Email inválido", violations.iterator().next().getMessage());
    }

    // Teste telefone inválido
    @Test
    void quandoTelefoneInvalido_deveRetornarViolacao() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Fulano");
        dto.setEmail("fulano@example.com");
        dto.setTelefone("123"); // telefone inválido, precisa ter 11 dígitos
        dto.setCnh("12345678901");
        dto.setCpf("12345678901");

        // endereço válido para não gerar violação aqui
        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setEstado("BA");
        endereco.setCidade("Salvador");
        endereco.setBairro("Centro");
        endereco.setLogradouro("Rua Exemplo");
        endereco.setIdentificacaoResidencial("123");
        dto.setEndereco(endereco);

        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(dto);

        // Espera só 1 violação (telefone inválido)
        assertEquals(1, violations.size());
        assertEquals("Telefone deve ter 11 dígitos", violations.iterator().next().getMessage());
    }

    // Teste válido, todos os campos preenchidos corretamente
    @Test
    void quandoTodosCamposValidos_deveNaoRetornarViolacoes() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Fulano de Tal");
        dto.setEmail("fulano@example.com");
        dto.setTelefone("71999999999");
        dto.setCnh("12345678901");
        dto.setCpf("12345678901");

        EnderecoDTO endereco = new EnderecoDTO();
        endereco.setEstado("BA");
        endereco.setCidade("Salvador");
        endereco.setBairro("Centro");
        endereco.setLogradouro("Rua Exemplo");
        endereco.setIdentificacaoResidencial("123");
        dto.setEndereco(endereco);

        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }
}
