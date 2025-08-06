package com.sgvv.ifba.service;

import com.sgvv.ifba.dto.CargoDTO;
import java.util.List;

public interface CargoService {

    CargoDTO salvar(CargoDTO cargoDTO);

    boolean deletar(Long id);

    List<CargoDTO> listarCargo();

}
