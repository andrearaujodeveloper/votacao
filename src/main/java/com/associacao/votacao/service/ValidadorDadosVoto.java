package com.associacao.votacao.service;

import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.model.Voto;
import com.associacao.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidadorDadosVoto {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private VotoRepository votoRepository;

    public Voto validarVoto(Voto voto) {
        Voto votoSalvo = votoRepository.findByIdPautaAndIdAssociado(voto.getPauta().getId(), voto.getAssociado().getId());
        if(votoSalvo != null) {
            throw new RuntimeException("Voto duplicado");
        }
        return voto;
    }
    public Pauta validarPauta(Long id){
        Pauta pauta = pautaService.buscarPautaPorId(id);
        if(!pauta.getAbertaVotacao()){
            throw new RuntimeException("Pauta fechada para votação");
        }
        return pauta;
    }

    public Associado validarAssociado(Long id) {
        Associado asssociado = associadoService.buscarAssociadoPorId(id);
        if(!asssociado.getAtivo()){
            throw new RuntimeException("Associado não está ativo");
        }
        return asssociado;
    }
}
