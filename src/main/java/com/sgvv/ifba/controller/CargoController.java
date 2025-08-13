package com.sgvv.ifba.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.service.CargoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping
    public ResponseEntity<CargoDTO> salvar(@RequestBody @Valid CargoDTO cargoDTO) {
        CargoDTO cargoSalvo = cargoService.salvar(cargoDTO);
        if (cargoSalvo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Retorna 400 Bad Request se não puder salvar
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = cargoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
