package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;

public record PautaDTO(
        @NotBlank(message = "Título é obrigatório") String titulo,
        @NotBlank(message = "Descrição é obrigatória") String descricao,
        Integer duracao
) {}
