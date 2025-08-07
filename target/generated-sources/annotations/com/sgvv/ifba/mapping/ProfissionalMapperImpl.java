package com.sgvv.ifba.mapping;

import com.sgvv.ifba.dto.ProfissionalDTO;
import com.sgvv.ifba.model.Profissional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T12:11:38-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class ProfissionalMapperImpl implements ProfissionalMapper {

    @Autowired
    private EspecialidadeMapper especialidadeMapper;

    @Override
    public Profissional toEntity(ProfissionalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Profissional profissional = new Profissional();

        profissional.setCpf( dto.getCpf() );
        if ( dto.getDataNascimento() != null ) {
            profissional.setDataNascimento( LocalDate.parse( dto.getDataNascimento() ) );
        }
        profissional.setEmail( dto.getEmail() );
        profissional.setEspecialidade( especialidadeMapper.toEntity( dto.getEspecialidade() ) );
        profissional.setId( dto.getId() );
        profissional.setNome( dto.getNome() );
        profissional.setTelefone( dto.getTelefone() );

        return profissional;
    }

    @Override
    public ProfissionalDTO toDTO(Profissional entity) {
        if ( entity == null ) {
            return null;
        }

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();

        profissionalDTO.setCpf( entity.getCpf() );
        if ( entity.getDataNascimento() != null ) {
            profissionalDTO.setDataNascimento( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getDataNascimento() ) );
        }
        profissionalDTO.setEmail( entity.getEmail() );
        profissionalDTO.setEspecialidade( especialidadeMapper.toDTO( entity.getEspecialidade() ) );
        profissionalDTO.setId( entity.getId() );
        profissionalDTO.setNome( entity.getNome() );
        profissionalDTO.setTelefone( entity.getTelefone() );

        return profissionalDTO;
    }
}
