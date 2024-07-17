package com.associacao.votacao.service;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.mapper.VotoMapper;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.model.Voto;
import com.associacao.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService implements IvotoService {

    @Autowired
    private ValidadorDadosVoto validadorDadosVoto;
    @Autowired
    private VotoRepository votoRepository;
    @Override
    public VotoResponse votar(VotoDTO votoDTO) {
        Pauta pauta = validadorDadosVoto.validarPauta(votoDTO.getIdPauta());
        Associado associado = validadorDadosVoto.validarAssociado(votoDTO.getIdAssociado());
        Voto voto = Voto.builder()
                .valorVoto(votoDTO.getValorVoto())
                .dataVoto(LocalDateTime.now())
                .pauta(pauta)
                .associado(associado)
                .build();
        voto = validadorDadosVoto.validarVoto(voto);

        return VotoMapper.INSTANCE.toResponse(votoRepository.save(voto));
    }

}
