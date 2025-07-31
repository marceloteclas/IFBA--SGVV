package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.model.NivelAcesso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NivelAcessoMapper {
    NivelAcesso toEntity(NivelAcessoDTO dto);
    NivelAcessoDTO toDto(NivelAcesso entity);
}
