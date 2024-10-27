package com.associacao.votacao.service;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.mapper.AssociadoMapper;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AssociadoService implements IAssociadoService {

    private AssociadoRepository associadoRepository;
    private AssociadoMapper mapper;

    @Transactional
    @Override
    public AssociadoResponse cadastrar(AssociadoDTO associadoDTO) {
        verificarEmailCadastrado(associadoDTO.email());
        verificarCPFCadastrado(associadoDTO.cpf());
        var associado = mapper.toEntity(associadoDTO);
        return mapper.toResponse(associadoRepository.save(associado));
    }

    @Override
    public Associado buscarAssociadoPorId(Long id) {
        return Optional.ofNullable(associadoRepository.findByIdAndAtivoTrue(id)).orElseThrow(()-> new NotFoundException("Associado não encontrado"));
    }

    @Override
    public void apagarLogicamente(Long id) {
        var associado = buscarAssociadoPorId(id);
        associado.inativar();
        associadoRepository.save(associado);
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
