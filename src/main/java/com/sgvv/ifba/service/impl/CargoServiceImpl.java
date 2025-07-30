package com.sgvv.ifba.service.impl;

import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.model.Cargo;
import com.sgvv.ifba.mapping.CargoMapper;
import com.sgvv.ifba.repository.CargoRepository;
import com.sgvv.ifba.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;

    @Override
    public CargoDTO salvar(CargoDTO cargoDTO) {
        Cargo entity = cargoMapper.toEntity(cargoDTO);
        Cargo saved = cargoRepository.save(entity);
        return cargoMapper.toDto(saved);
    }

     @Override
    public boolean deletar(Long id) {
        if (!cargoRepository.existsById(id)) {
            return false;
        }
        cargoRepository.deleteById(id);
        return true;
    }
   
    @Override
    public List<CargoDTO> listarCargo() {
        return cargoRepository.findAll()
                .stream()
                .map(cargoMapper::toDto)
                .toList();
    }
    
}
