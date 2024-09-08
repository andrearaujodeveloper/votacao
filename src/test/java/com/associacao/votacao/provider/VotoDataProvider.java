package com.associacao.votacao.provider;

import com.associacao.votacao.dto.ValorVotoEnum;
import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.model.Voto;

import java.time.LocalDateTime;

public class VotoDataProvider {

    public static Voto criar() {
        var voto = new Voto();
        voto.setId(1l);
        voto.setValorVoto(ValorVotoEnum.SIM);
        voto.setPauta(PautaDataProvider.criar());
        voto.setAssociado(AssociadoDataProvider.criar());
        return voto;
    }

    public static Voto criarVotoParaTesteRepository(Pauta pauta, Associado associado) {
        var voto = new Voto();
        voto.setValorVoto(ValorVotoEnum.SIM);
        voto.setDataVoto(LocalDateTime.now());
        voto.setPauta(pauta);
        voto.setAssociado(associado);
        return voto;
    }

    public static VotoDTO criarDTO() {
        return new VotoDTO(ValorVotoEnum.SIM, 1l,1l);
    }

    public static VotoResponse criarVotoResponse() {
        return new VotoResponse(1l, "SIM", LocalDateTime.now());
    }
}
