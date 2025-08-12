package com.sgvv.ifba.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importe para controle transacional

import com.sgvv.ifba.dto.VeiculoDTO;
import com.sgvv.ifba.model.Veiculo; // Importar a entidade Veiculo
import com.sgvv.ifba.repository.VeiculoRepository;
import com.sgvv.ifba.service.VeiculoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Cria um construtor com os atributos final, ideal para injeção de dependência
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Override
    @Transactional // Garante que a operação seja transacional (commit ou rollback)
    public VeiculoDTO salvar(VeiculoDTO veiculoDTO) {
        // Mapeia o DTO para a entidade
        Veiculo veiculo = new Veiculo();
        // Não defina o ID aqui, ele será gerado automaticamente
        veiculo.setRenavam(veiculoDTO.getRenavam());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setChassi(veiculoDTO.getChassi());
        veiculo.setCombustivel(veiculoDTO.getCombustivel());
        veiculo.setPotencia(veiculoDTO.getPotencia());
        veiculo.setCilindrada(veiculoDTO.getCilindrada());
        veiculo.setAtivo(veiculoDTO.isAtivo()); // Use isAtivo para boolean

        // Salva a entidade no banco de dados
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        
        // Mapeia a entidade salva de volta para um DTO e retorna
        veiculoDTO.setId(veiculoSalvo.getId()); // Atualiza o DTO com o ID gerado
        return veiculoDTO;
    }

    @Override
    @Transactional(readOnly = true) // Transação somente leitura, otimizada para consultas
    public Optional<VeiculoDTO> buscarPorId(Long id) {
        // Busca a entidade pelo ID
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        
        // Se encontrar, mapeia para DTO e retorna. Caso contrário, retorna Optional vazio.
        return veiculoOptional.map(veiculo -> {
            VeiculoDTO veiculoDTO = new VeiculoDTO();
            veiculoDTO.setId(veiculo.getId());
            veiculoDTO.setRenavam(veiculo.getRenavam());
            veiculoDTO.setPlaca(veiculo.getPlaca());
            veiculoDTO.setMarca(veiculo.getMarca());
            veiculoDTO.setModelo(veiculo.getModelo());
            veiculoDTO.setAno(veiculo.getAno());
            veiculoDTO.setChassi(veiculo.getChassi());
            veiculoDTO.setCombustivel(veiculo.getCombustivel());
            veiculoDTO.setPotencia(veiculo.getPotencia());
            veiculoDTO.setCilindrada(veiculo.getCilindrada());
            veiculoDTO.setAtivo(veiculo.isAtivo()); // Use isAtivo para boolean
            return veiculoDTO;
        });
    }

    @Override
    @Transactional
    public Optional<VeiculoDTO> atualizarPorId(Long id, VeiculoDTO veiculoDTO) {
        // Verifica se o veículo existe
        return veiculoRepository.findById(id).map(veiculoExistente -> {
            // Atualiza os campos da entidade existente com os dados do DTO
            veiculoExistente.setRenavam(veiculoDTO.getRenavam());
            veiculoExistente.setPlaca(veiculoDTO.getPlaca());
            veiculoExistente.setMarca(veiculoDTO.getMarca());
            veiculoExistente.setModelo(veiculoDTO.getModelo());
            veiculoExistente.setAno(veiculoDTO.getAno());
            veiculoExistente.setChassi(veiculoDTO.getChassi());
            veiculoExistente.setCombustivel(veiculoDTO.getCombustivel());
            veiculoExistente.setPotencia(veiculoDTO.getPotencia());
            veiculoExistente.setCilindrada(veiculoDTO.getCilindrada());
            veiculoExistente.setAtivo(veiculoDTO.isAtivo()); // Use isAtivo para boolean

            // Salva as alterações (o método save do JPA atualiza se o ID já existe)
            Veiculo veiculoAtualizado = veiculoRepository.save(veiculoExistente);

            // Mapeia a entidade atualizada de volta para DTO e retorna
            veiculoDTO.setId(veiculoAtualizado.getId()); // Garante que o ID correto está no DTO de retorno
            return veiculoDTO;
        });
        // Se o findById não encontrar, o map não será executado e um Optional vazio será retornado.
    }

    @Override
    @Transactional
    public boolean deletar(Long id) {
        if (veiculoRepository.existsById(id)) {
            veiculoRepository.deleteById(id);
            return true; // Retorna true se a exclusão foi bem-sucedida
        }
        return false; // Retorna false se o ID não foi encontrado
    }
    
}