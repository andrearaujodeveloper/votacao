package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssociadoDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
}
