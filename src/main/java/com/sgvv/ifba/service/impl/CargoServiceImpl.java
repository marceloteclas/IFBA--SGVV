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
    @Transactional // Garante que a operação seja transacional (commit ou rollback)
    public CargoDTO salvar(CargoDTO cargoDTO) {
        // Mapeia o DTO para a entidade
        Cargo cargo = new Cargo();
        cargo.setId(cargoDTO.getId()); // Se o ID for fornecido, atualiza o cargo existente
        cargo.setNomeCargo(cargoDTO.getNomeCargo());

        // Salva a entidade no banco de dados
        Cargo cargoSalvo = cargoRepository.save(cargo);

        // Mapeia a entidade salva de volta para um DTO e retorna
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
