package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDTO(@NotBlank String login,@NotBlank String senha) {
}
