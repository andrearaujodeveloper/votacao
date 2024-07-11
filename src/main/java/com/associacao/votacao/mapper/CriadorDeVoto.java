package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.model.Voto;
import com.associacao.votacao.service.AssociadoService;
import com.associacao.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CriadorDeVoto {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoService associadoService;


    public  Voto criar(VotoDTO votoDTO) {
        Voto voto = new Voto();
        voto.setValorVoto(votoDTO.getValorVoto());
        voto.setPauta(pautaService.buscarPautaPorId(votoDTO.getIdPauta()));
        voto.setAssociado(associadoService.buscarAssociadoPorId(votoDTO.getIdAssociado()));
        voto.setDataVoto(LocalDateTime.now());
        return voto;
    }
}
