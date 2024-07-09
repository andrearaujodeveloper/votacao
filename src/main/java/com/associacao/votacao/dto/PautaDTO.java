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
public class PautaDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

}
