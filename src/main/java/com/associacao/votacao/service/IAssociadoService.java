package com.associacao.votacao.service;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.model.Associado;
import org.springframework.stereotype.Component;

@Component
public interface IAssociadoService {

    AssociadoResponse cadastrar(AssociadoDTO associadoDTO);

    Associado buscarAssociadoPorId(Long id);
}
