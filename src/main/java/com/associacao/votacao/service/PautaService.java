package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.dto.PautaResultadoResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.mapper.PautaMapper;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PautaService implements IPautaService{
    
    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public PautaResponse cadastrar(PautaDTO pautaDTO) {
        verificaDuplicidadeDePauta(pautaDTO);
        Pauta pauta = PautaMapper.INSTANCE.toEntity(pautaDTO);
        var response = PautaMapper.INSTANCE.toResponse(pautaRepository.save(pauta));
        return response;
    }

    @Override
    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id).orElseThrow(()->new RuntimeException("Pauta não encontrada"));
    }

    @Override
    public PautaResponse liberarVotacao(Long id) {
        var pauta = buscarPautaPorId(id);
        if (pauta.pautaFoiEncerrada() || !pauta.getAbertaVotacao()) {
            throw new DomainBusinessException("Não foi possível colocar pauta em votação");
        }

        pauta.liberarParaVotacao();
        return PautaMapper.INSTANCE.toResponse(pautaRepository.save(pauta));
    }

    @Override
    public PautaResultadoResponse contarVotos(Long id) {
        PautaResultadoProjection projection = pautaRepository.contarVotos(id);
        return new PautaResultadoResponse(projection.getTitulo(), projection.getDescricao(), projection.getVotosPositivos(), projection.getVotosNegativos());
    }

    private void verificaDuplicidadeDePauta(PautaDTO pautaDTO) {
        if(pautaRepository.existsByTituloAndDescricao(pautaDTO.getTitulo(), pautaDTO.getDescricao())){
            throw new DomainBusinessException("Duplicidade de cadastro");
        }
    }

}
