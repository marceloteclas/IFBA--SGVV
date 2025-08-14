package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.model.Cargo;
import com.sgvv.ifba.repository.CargoRepository;
import com.sgvv.ifba.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    @Override
    @Transactional
    public CargoDTO salvar(CargoDTO cargoDTO) {
        // Verifica duplicidade
        if (cargoRepository.existsByNomeCargo(cargoDTO.getNomeCargo())) {
            throw new IllegalArgumentException("Já existe um cargo com esse nome.");
        }

        Cargo cargo = new Cargo();
        cargo.setId(cargoDTO.getId());
        cargo.setNomeCargo(cargoDTO.getNomeCargo());

        Cargo cargoSalvo = cargoRepository.save(cargo);

        cargoDTO.setId(cargoSalvo.getId());
        return cargoDTO;
    }

    @Override
    public boolean deletar(Long id) {
        if (!cargoRepository.existsById(id)) {
            return false;
        }
        cargoRepository.deleteById(id);
        return true;
    }
}
