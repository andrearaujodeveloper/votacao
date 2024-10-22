package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioTrocaSenhaDTO(
        @NotNull(message = "identificador obrigatório") Long id,
        @NotBlank(message = "usuario é obrigatório") String login,
        @NotBlank(message = "senha é obrigatória")String novaSenha,
        @NotBlank(message = "confirmação de senha obrigatória")String confirmacao) {
}
