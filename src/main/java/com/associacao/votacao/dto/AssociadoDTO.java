package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;

public record AssociadoDTO(
        @NotBlank(message = "Nome é obrigatório") String nome,
        @NotBlank(message = "CPF é obrigatório") String cpf,
        @NotBlank(message = "Email é obrigatório") String email
) {}
