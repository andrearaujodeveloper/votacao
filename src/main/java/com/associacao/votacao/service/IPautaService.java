package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.model.Pauta;
import org.springframework.stereotype.Component;

@Component
public interface IPautaService {

    PautaResponse cadastrar(PautaDTO pautaDTO);

    Pauta buscarPautaPorId(Long id);
}
