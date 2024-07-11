package com.associacao.votacao.service;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.mapper.CriadorDeVoto;
import com.associacao.votacao.mapper.VotoMapper;
import com.associacao.votacao.model.Voto;
import com.associacao.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoService implements IvotoService {

    @Autowired
    private CriadorDeVoto criadorDeVoto;

    @Autowired
    private VotoRepository votoRepository;
    @Override
    public VotoResponse votar(VotoDTO votoDTO) {
        Voto voto = criadorDeVoto.criar(votoDTO);
        if(!validarVoto(voto)){
           throw new RuntimeException("Não foi possível registrar voto");
        }

        return VotoMapper.INSTANCE.toResponse(voto);
    }

    private Boolean validarVoto(Voto voto) {
        return voto.getPauta().getAbertaVotacao() &&
                voto.getAssociado().getAtivo() &&
                !votoRepository.existsByPautaIdAndAssociadoId(voto.getPauta().getId(), voto.getAssociado().getId());
    }

}
