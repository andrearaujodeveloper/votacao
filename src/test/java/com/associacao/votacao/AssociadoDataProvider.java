package com.associacao.votacao;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.model.Associado;

import java.util.List;

public class AssociadoDataProvider {

    public static Associado criar() {
        return new Associado(1l, "Associado de Teste", "01234567891","associadoTeste@email.com", true, List.of());
    }

    public static AssociadoDTO criarDTO() {
        return new AssociadoDTO("Associado de Teste", "01234567891","associadoTeste@email.com");
    }

    public static AssociadoResponse criarResponse() {
        return new AssociadoResponse(1l, "Associado de Teste", "01234567891","associadoTeste@email.com");
    }
}
