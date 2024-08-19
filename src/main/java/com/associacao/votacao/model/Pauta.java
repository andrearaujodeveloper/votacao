package com.associacao.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

//@SQLRestriction("abertaVotacao = true") restrição na clausula where automaticamente em todas as querys
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
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @Nullable
    private LocalDateTime dataAbertura =  null;
    @Nullable
    private LocalDateTime dataFechamento = null;
    @NotNull
    private Integer duracao = 1 ;
    @NotNull
    private Boolean abertaVotacao = false;
    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY)
    private List<Voto> votos;


    public boolean pautaFoiEncerrada(){
        return !this.getAbertaVotacao() && this.getDataAbertura() != null;
    }

    public void liberarParaVotacao() {
        this.abertaVotacao = true;
        this.dataAbertura = LocalDateTime.now();
        this.dataFechamento = LocalDateTime.now().plusMinutes(this.duracao);
    }

    public void finalizarvotacao() {
        this.abertaVotacao = false;
    }

    public void inicializarDuracao(Integer duracao) {
        if(duracao == null){
            this.duracao = 1;
        }else {
            this.duracao = duracao;
        }
    }
}
