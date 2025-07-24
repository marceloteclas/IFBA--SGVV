package com.sgvv.ifba.controller;

import com.sgvv.ifba.dto.EnderecoDTO;
import com.sgvv.ifba.service.EnderecoService;
import jakarta.validation.Valid; // Importe para usar @Valid
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/enderecos") // Define o caminho base para todos os endpoints neste controlador
@RequiredArgsConstructor // Injeta o EnderecoService via construtor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping // Mapeia requisições POST para /enderecos
    public ResponseEntity<EnderecoDTO> salvarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        // @RequestBody converte o JSON do corpo da requisição para EnderecoDTO
        // @Valid aciona as validações definidas no EnderecoDTO (ex: @NotNull)
        EnderecoDTO enderecoSalvo = enderecoService.salvar(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo); // Retorna 201 Created com o DTO salvo
    }

    @GetMapping("/{id}") // Mapeia requisições GET para /enderecos/{id}
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable Long id) {
        // @PathVariable extrai o ID da URL
        Optional<EnderecoDTO> enderecoDTO = enderecoService.buscarPorId(id);
        return enderecoDTO.map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o DTO
                          .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para /enderecos/{id}
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, 
                                                         @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Optional<EnderecoDTO> enderecoAtualizado = enderecoService.atualizarPorId(id, enderecoDTO);
        return enderecoAtualizado.map(ResponseEntity::ok) // Se atualizar, retorna 200 OK com o DTO atualizado
                                 .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /enderecos/{id}
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        boolean deletado = enderecoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o ID não existir
    }
}