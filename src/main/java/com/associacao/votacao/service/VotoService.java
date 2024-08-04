package com.associacao.votacao.service;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.mapper.VotoMapper;
import com.associacao.votacao.model.Voto;
import com.associacao.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService implements IvotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoService associadoService;
    @Override
    public VotoResponse votar(VotoDTO votoDTO) {
        var pauta = pautaService.buscarPautaPorId(votoDTO.getIdPauta());
        var associado = associadoService.buscarAssociadoPorId(votoDTO.getIdAssociado());
        var voto = Voto.builder()
                .valorVoto(votoDTO.getValorVoto())
                .dataVoto(LocalDateTime.now())
                .pauta(pauta)
                .associado(associado)
                .build();

        validarVoto(voto);

        return VotoMapper.INSTANCE.toResponse(votoRepository.save(voto));
    }

    private void validarVoto(Voto voto) {
        Voto votoSalvo = votoRepository.findByIdPautaAndIdAssociado(voto.getPauta().getId(), voto.getAssociado().getId());
        var votoInvalido = votoSalvo != null || !voto.getAssociado().getAtivo() || !voto.getPauta().getAbertaVotacao();

        if(votoInvalido){
            throw new DomainBusinessException("Voto Inválido.");
        }
    }

}
