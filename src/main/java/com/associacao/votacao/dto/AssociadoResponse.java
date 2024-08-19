package com.associacao.votacao.dto;

public record AssociadoResponse(
        Long id,
        String nome,
        String cpf,
        String email
) {}
