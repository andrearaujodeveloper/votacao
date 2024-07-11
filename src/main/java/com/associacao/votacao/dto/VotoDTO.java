package com.associacao.votacao.dto;

import com.associacao.votacao.model.Associado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VotoDTO {
    private String valorVoto;
    private Long idPauta;
    private Long idAssociado;
}
