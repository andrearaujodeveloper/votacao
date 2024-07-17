package com.associacao.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ASSOCIADOS")
@Entity
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
    private Boolean ativo;
    @OneToMany(mappedBy = "associado", fetch = FetchType.LAZY)
    private List<Voto> votos;
}
