package com.associacao.votacao.model;

import com.associacao.votacao.dto.ValorVotoEnum;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ValorVotoEnum valorVoto;

    private LocalDateTime dataVoto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAUTA")
    private Pauta pauta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ASSOCIADO")
    private Associado associado;

    public void registrarDataVoto(){
        this.dataVoto = LocalDateTime.now();
    }
}
