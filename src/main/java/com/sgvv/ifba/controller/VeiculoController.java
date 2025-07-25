package com.sgvv.ifba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgvv.ifba.dto.VeiculoDTO;
import com.sgvv.ifba.service.VeiculoService;

import jakarta.validation.Valid; // Importe para usar @Valid
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping // Mapeia requisições POST para /veiculos
    public ResponseEntity<VeiculoDTO> salvarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO) {
        // @RequestBody converte o JSON do corpo da requisição para VeiculoDTO
        // @Valid aciona as validações definidas no VeiculoDTO (ex: @NotNull)
        VeiculoDTO veiculoSalvo = veiculoService.salvar(veiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo); // Retorna 201 Created com o DTO salvo
    }

    @GetMapping("/{id}") // Mapeia requisições GET para /veiculos/{id}
    public ResponseEntity<VeiculoDTO> buscarVeiculoPorId(@PathVariable Long id) {
        // @PathVariable extrai o ID da URL
        Optional<VeiculoDTO> veiculoDTO = veiculoService.buscarPorId(id);
        return veiculoDTO.map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o DTO
                          .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para /veiculos/{id}
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, 
                                                         @RequestBody @Valid VeiculoDTO veiculoDTO) {
        Optional<VeiculoDTO> veiculoAtualizado = veiculoService.atualizarPorId(id, veiculoDTO);
        return veiculoAtualizado.map(ResponseEntity::ok) // Se atualizar, retorna 200 OK com o DTO atualizado
                                 .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para /veiculos/{id}
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        boolean deletado = veiculoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o ID não existir
    }
}