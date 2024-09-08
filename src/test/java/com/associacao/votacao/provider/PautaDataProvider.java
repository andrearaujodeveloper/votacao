package com.associacao.votacao.provider;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.model.Pauta;

import java.time.LocalDateTime;

public class PautaDataProvider {

    public static Pauta criar() {
        var pauta = new Pauta();
        pauta.setId(1l);
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        pauta.setDataCriacao(LocalDateTime.now());
        return pauta;
    }

    public static Pauta criarSemId() {
        var pauta = new Pauta();
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        pauta.setDataCriacao(LocalDateTime.now());
        return pauta;
    }

    public static PautaDTO criarDTO() {
        return new PautaDTO("titulo teste", "descricao teste", 5);
    }

    public static PautaResponse criarResponse() {
        return new PautaResponse(1l, "titulo teste", "descricao teste", LocalDateTime.now());

    }
}
