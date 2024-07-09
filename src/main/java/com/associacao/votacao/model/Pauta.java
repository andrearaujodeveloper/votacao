package com.associacao.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "PAUTAS")
@Entity
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private LocalDateTime dataCriacao;
    @Nullable
    private LocalDateTime dataAbertura;
    @Nullable
    private LocalDateTime dataFechamento;
    @NotNull
    private Boolean abertaVotacao;
    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos;

}
