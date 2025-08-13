package com.sgvv.ifba.service;

import com.sgvv.ifba.dto.CargoDTO;

public interface CargoService {

    CargoDTO salvar(CargoDTO cargoDTO);

    boolean deletar(Long id);

}
