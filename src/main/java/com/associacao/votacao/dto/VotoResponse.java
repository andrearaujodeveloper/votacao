package com.associacao.votacao.dto;

import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VotoResponse {
    private Long id;
    private String valorVoto;
    private LocalDateTime dataVoto;
    private Pauta tituloPauta;
    private Associado nomeAssociado;
}
