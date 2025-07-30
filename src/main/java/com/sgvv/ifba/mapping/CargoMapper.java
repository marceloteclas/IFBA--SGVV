package com.sgvv.ifba.mapping;

import org.mapstruct.Mapper;
import com.sgvv.ifba.dto.CargoDTO;
import com.sgvv.ifba.model.Cargo;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    Cargo toEntity(CargoDTO dto);

   CargoDTO toDTO(Cargo entity);
    
}
