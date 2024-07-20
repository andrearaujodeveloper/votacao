package com.associacao.votacao.dto;

public interface PautaResultadoProjection {
    String getTitulo();
    String getDescricao();
    Integer getVotosPositivos();
    Integer getVotosNegativos();
}
