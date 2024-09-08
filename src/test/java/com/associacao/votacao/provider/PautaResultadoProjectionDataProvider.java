package com.associacao.votacao.provider;

import com.associacao.votacao.dto.PautaResultadoProjection;

public class PautaResultadoProjectionDataProvider {

    public static PautaResultadoProjection criar() {
        return new PautaResultadoProjection() {
            @Override
            public String getTitulo() {
                return "titulo Teste";
            }

            @Override
            public String getDescricao() {
                return "descricao Teste";
            }

            @Override
            public Integer getVotosPositivos() {
                return 1;
            }

            @Override
            public Integer getVotosNegativos() {
                return 2;
            }
        };
    }
}
