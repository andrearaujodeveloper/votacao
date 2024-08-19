package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.dto.PautaResultadoResponse;
import com.associacao.votacao.model.Pauta;
import org.springframework.stereotype.Component;

@Component
public interface IPautaService {

    PautaResponse cadastrar(PautaDTO pautaDTO);

    Pauta buscarPautaPorId(Long id);

    void liberarVotacao(Long id);

    PautaResultadoProjection contarVotos(Long id);
}
