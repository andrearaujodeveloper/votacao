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
        List<String> errosValidacao = validarDadosDeCadastro(associadoDTO);
        if(!errosValidacao.isEmpty()) {
            throw new DomainBusinessException(errosValidacao.toString());
        }
        Associado associado = AssociadoMapper.INSTANCE.toEntity(associadoDTO);
        return AssociadoMapper.INSTANCE.toResponse(associadoRepository.save(associado));
    }

    @Override
    public Associado buscarAssociadoPorId(Long id) {
        return associadoRepository.findById(id).orElseThrow(()-> new RuntimeException("Associado não encontrado"));
    }

    private List<String> validarDadosDeCadastro(AssociadoDTO associadoDTO){
        List<String> erros = new ArrayList<>();

        if(associadoRepository.existsByEmail(associadoDTO.getEmail())){
            erros.add("E-mail já cadastrado");
        };

        if(associadoRepository.existsByCpf(associadoDTO.getCpf())){
            erros.add("CPF já cadastrado");
        };


        return erros;
    }
}
