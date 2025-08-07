package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.EspecialidadeDTO;
import com.sgvv.ifba.model.Especialidade;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T12:11:07-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class EspecialidadeMapperImpl implements EspecialidadeMapper {

    @Override
    public Especialidade toEntity(EspecialidadeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Especialidade especialidade = new Especialidade();

        especialidade.setId( dto.getId() );
        especialidade.setNome( dto.getNome() );

        return especialidade;
    }

    @Override
    public EspecialidadeDTO toDTO(Especialidade entity) {
        if ( entity == null ) {
            return null;
        }

        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();

        especialidadeDTO.setId( entity.getId() );
        especialidadeDTO.setNome( entity.getNome() );

        return especialidadeDTO;
    }
}
