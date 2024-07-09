package com.associacao.votacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssociadoResponse {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
}
