package com.associacao.votacao.service;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.mapper.VotoMapper;
import com.associacao.votacao.model.Voto;
import com.associacao.votacao.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VotoService implements IvotoService {

    private VotoRepository votoRepository;
    private PautaService pautaService;
    private AssociadoService associadoService;

    private VotoMapper mapper;
    @Override
    public VotoResponse votar(VotoDTO votoDTO) {
        var pauta = pautaService.buscarPautaPorId(votoDTO.idPauta());
        var associado = associadoService.buscarAssociadoPorId(votoDTO.idAssociado());
        var voto = mapper.toEntity(votoDTO, pauta, associado);
        voto.registrarDataVoto();
        validarVoto(voto);

        return mapper.toResponse(votoRepository.save(voto));
    }

    private void validarVoto(Voto voto) {
        Voto votoSalvo = votoRepository.findByIdPautaAndIdAssociado(voto.getPauta().getId(), voto.getAssociado().getId());
        var votoInvalido = votoSalvo != null || !voto.getAssociado().getAtivo() ;

        if(votoInvalido){
            throw new DomainBusinessException("Voto Inválido.");
        }
    }

}
