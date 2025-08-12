package com.sgvv.ifba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.sgvv.ifba.dto.NivelAcessoDTO;
import com.sgvv.ifba.mapping.NivelAcessoMapper;
import com.sgvv.ifba.model.NivelAcesso;
import com.sgvv.ifba.repository.NivelAcessoRepository;
import com.sgvv.ifba.service.NivelAcessoService;

@Service
public class NivelAcessoServiceImpl implements NivelAcessoService {

    private final NivelAcessoRepository nivelAcessoRepository;
    private final NivelAcessoMapper nivelAcessoMapper;

    public NivelAcessoServiceImpl(NivelAcessoRepository nivelAcessoRepository, NivelAcessoMapper nivelAcessoMapper) {
        this.nivelAcessoRepository = nivelAcessoRepository;
        this.nivelAcessoMapper = nivelAcessoMapper;
    };

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

    @Override
    public List<NivelAcessoDTO> listarNivelAcesso() {
        return nivelAcessoRepository.findAll()
                .stream()
                .map(nivelAcessoMapper::toDto)
                .toList();

    }

}
