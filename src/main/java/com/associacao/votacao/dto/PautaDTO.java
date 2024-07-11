package com.associacao.votacao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PautaDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @Nullable
    private Integer duracao;

}
