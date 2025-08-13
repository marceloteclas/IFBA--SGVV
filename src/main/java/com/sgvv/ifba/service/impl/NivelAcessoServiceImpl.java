package com.sgvv.ifba.service.impl;

import org.springframework.stereotype.Service;
import com.sgvv.ifba.dto.NivelAcessoDTO;

import com.sgvv.ifba.model.NivelAcesso;
import com.sgvv.ifba.repository.NivelAcessoRepository;
import com.sgvv.ifba.service.NivelAcessoService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class NivelAcessoServiceImpl implements NivelAcessoService {

    private final NivelAcessoRepository nivelAcessoRepository;

    @Override
    public NivelAcessoDTO salvar(NivelAcessoDTO nivelDTO) {

        NivelAcesso nivelAcesso = new NivelAcesso();
        nivelAcesso.setId(nivelDTO.getId());
        nivelAcesso.setNivel(nivelDTO.getNivel());

        NivelAcesso savedNivelAcesso = nivelAcessoRepository.save(nivelAcesso);
        nivelDTO.setId(savedNivelAcesso.getId());
        return nivelDTO;

    }

    @Override
    public boolean deletar(Long id) {
        if (!nivelAcessoRepository.existsById(id)) {
            return false;
        }
        nivelAcessoRepository.deleteById(id);
        return true;
    }

}
