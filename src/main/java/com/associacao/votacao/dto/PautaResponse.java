package com.associacao.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PautaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
}
