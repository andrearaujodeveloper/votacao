package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.dto.PautaResultadoResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.mapper.PautaMapper;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.associacao.votacao.util.Mensagens.LIBERADA_PARA_VOTACAO;

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
        var response = mapper.toResponse(pautaRepository.save(pauta));
        return response;
    }

    @Override
    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id).orElseThrow(()->new NotFoundException("Pauta não encontrada"));
    }

    @Override
    public String liberarVotacao(Long id) {
        var pauta = buscarPautaPorId(id);
        verificaSePoderSerLiberadaParavotacao(pauta);
        pauta.liberarParaVotacao();
        PautaMapper.INSTANCE.toResponse(pautaRepository.save(pauta));

        return LIBERADA_PARA_VOTACAO;
    }

    @Override
    public PautaResultadoResponse contarVotos(Long id) {
        PautaResultadoProjection projection = pautaRepository.contarVotos(id);
        return new PautaResultadoResponse(projection.getTitulo(), projection.getDescricao(), projection.getVotosPositivos(), projection.getVotosNegativos());
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
