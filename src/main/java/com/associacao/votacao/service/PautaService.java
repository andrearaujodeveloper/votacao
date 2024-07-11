package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.mapper.PautaMapper;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService implements IPautaService{
    
    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public PautaResponse cadastrar(PautaDTO pautaDTO) {
        if(verificaDuplicidadeDePauta(pautaDTO)){
            throw new RuntimeException("Duplicidade de cadastro");
        }
        Pauta pauta = PautaMapper.INSTANCE.toEntity(pautaDTO);
        return PautaMapper.INSTANCE.toResponse(pautaRepository.save(pauta));
    }

    @Override
    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id).orElseThrow(()->new RuntimeException("Pauta não encontrada"));
    }

    private boolean verificaDuplicidadeDePauta(PautaDTO pautaDTO){
        return pautaRepository.existsByTituloAndDescricao(pautaDTO.getTitulo(), pautaDTO.getDescricao());
    }
}
