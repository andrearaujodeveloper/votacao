package com.associacao.votacao.service;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.mapper.AssociadoMapper;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociadoService implements IAssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;
    @Override
    public AssociadoResponse cadastrar(AssociadoDTO associadoDTO) {
        verificarEmailCadastrado(associadoDTO.getEmail());
        verificarCPFCadastrado(associadoDTO.getCpf());
        Associado associado = AssociadoMapper.INSTANCE.toEntity(associadoDTO);
        return AssociadoMapper.INSTANCE.toResponse(associadoRepository.save(associado));
    }

    @Override
    public Associado buscarAssociadoPorId(Long id) {
        return associadoRepository.findById(id).orElseThrow(()-> new RuntimeException("Associado não encontrado"));
    }
    private void verificarEmailCadastrado(String email){
        if(associadoRepository.existsByEmail(email)) {
            throw new DomainBusinessException("E-mail jpa cadastrado.");
        }
    }

    private void verificarCPFCadastrado(String cpf){
        if(associadoRepository.existsByCpf(cpf)) {
            throw new DomainBusinessException("CPF jpa cadastrado.");
        }
    }
}
