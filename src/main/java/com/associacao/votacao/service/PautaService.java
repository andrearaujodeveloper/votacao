package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.mapper.PautaMapper;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PautaService implements IPautaService{
    private PautaRepository pautaRepository;

    private PautaMapper mapper;
    @Override
    public PautaResponse cadastrar(PautaDTO pautaDTO) {
        verificaDuplicidadeDePauta(pautaDTO);
        Pauta pauta = mapper.toEntity(pautaDTO);
        pauta.inicializarDuracao(pautaDTO.duracao());
        return mapper.toResponse(pautaRepository.save(pauta));
    }

    @Override
    public Pauta buscarPautaAbertaPorId(Long id) {
        return Optional.ofNullable(pautaRepository.findByIdAndAbertaVotacaoTrue(id)).orElseThrow(()->new NotFoundException("Pauta não encontrada"));
    }

    @Override
    public void liberarVotacao(Long id) {
        var pauta = pautaRepository.findById(id).orElseThrow(()->new NotFoundException("Pauta não encontrada"));
        verificaSePoderSerLiberadaParavotacao(pauta);
        pauta.liberarParaVotacao();
        pautaRepository.save(pauta);
    }

    @Override
    public PautaResultadoProjection contarVotos(Long id) {
        return pautaRepository.contarVotos(id);
    }

    private void verificaDuplicidadeDePauta(PautaDTO pautaDTO) {
        if(pautaRepository.existsByTituloAndDescricao(pautaDTO.titulo(), pautaDTO.descricao())){
            throw new DomainBusinessException("Duplicidade de cadastro");
        }
    }

    public void verificaSePoderSerLiberadaParavotacao(Pauta pauta) {
        if (pauta.pautaFoiEncerrada() || pauta.getAbertaVotacao()) {
            throw new DomainBusinessException("Não foi possível colocar pauta em votação");
        }
    }

}
