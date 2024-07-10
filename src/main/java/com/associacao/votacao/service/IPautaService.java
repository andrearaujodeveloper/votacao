package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import org.springframework.stereotype.Component;

@Component
public interface IPautaService {

    PautaResponse cadastrar(PautaDTO pautaDTO);
}
