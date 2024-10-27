package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotNull;

public record VotoDTO(@NotNull(message = "Não pode ser nulo") ValorVotoEnum valorVoto,
                      @NotNull(message = "Não pode ser nulo") Long idPauta,
                      @NotNull(message = "Não pode ser nulo") Long idAssociado) {}
