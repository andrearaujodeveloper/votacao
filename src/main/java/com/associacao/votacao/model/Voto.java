package com.associacao.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "VOTOS")
@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String valorVoto;
    @NotNull
    private LocalDateTime dataVoto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAUTA")
    private Pauta pauta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ASSOCIADO")
    private Associado associado;
}
