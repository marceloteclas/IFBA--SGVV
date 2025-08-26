package com.sgvv.ifba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.service.NivelAcessoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nivelAcesso")
@RequiredArgsConstructor
public class NivelAcessoController {

    private final NivelAcessoService nivelAcessoService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid NivelAcessoDTO nivelAcessoDTO) {
        try {
            NivelAcessoDTO salvo = nivelAcessoService.salvar(nivelAcessoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = nivelAcessoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}