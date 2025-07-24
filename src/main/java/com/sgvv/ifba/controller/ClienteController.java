package com.sgvv.ifba.controller;

import com.sgvv.ifba.dto.ClienteDTO;
import com.sgvv.ifba.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO salvo = clienteService.salvar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<ClienteDTO>> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarClientePorCpf(cpf));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id,
                                                 @RequestBody @Valid ClienteDTO clienteDTO) {
        Optional<ClienteDTO> atualizado = clienteService.atualizarPorId(id, clienteDTO);
        return atualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = clienteService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
