package com.associacao.votacao.util;

import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@EnableScheduling
@Component
public class FinalizadorDePautas {

    @Autowired
    private PautaRepository pautaRepository;

    @Scheduled(fixedDelay = 60000)
    public void fecharVotacao(){
        var pautas =  pautaRepository.findAllByAbertaVotacaoTrue();
        pautas.forEach( p ->{
            if(deveFecharPauta(p)) {
                p.finalizarVOtacao();
                pautaRepository.save(p);
            }
        });

    }

    private boolean deveFecharPauta(Pauta p) {
        return LocalDateTime.now().isAfter(p.getDataFechamento());
    }
}
