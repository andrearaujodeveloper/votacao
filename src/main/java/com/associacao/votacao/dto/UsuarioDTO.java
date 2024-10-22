package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "Login é obrigatório") String login
) {}
