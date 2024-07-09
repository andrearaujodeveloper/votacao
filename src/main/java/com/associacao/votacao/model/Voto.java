package com.associacao.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @JoinColumn(name = "PAUTA_ID")
    private Pauta pauta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSOCIADO_ID")
    private Associado associado;
}
